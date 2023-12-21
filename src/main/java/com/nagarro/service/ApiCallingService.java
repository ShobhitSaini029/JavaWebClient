package com.nagarro.service;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nagarro.apiData.GenderiseUserApiData;
import com.nagarro.apiData.NationalizeUserApiData;
import com.nagarro.apiData.RandomUserApiData;
import com.nagarro.entities.UserEntity;
import com.nagarro.repository.UserRepo;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import com.nagarro.service.UserVlidationService;

@Service
public class ApiCallingService {

	@Autowired
	@Qualifier("webClientFirstApi")
	private WebClient.Builder webClientfirstApi;

	@Autowired
	@Qualifier("webClienSecondApi")
	private WebClient.Builder webCliensecondApi;

	@Autowired
	@Qualifier("webClientThirdApi")
	private WebClient.Builder webClientthirdApi;

	@Autowired
	private UserVlidationService userValidation;

	@Autowired
	private UserRepo userRepo;

	private ExecutorService executorService = Executors.newFixedThreadPool(2);

	public Mono<UserEntity> fetchUserDataFromApi() {
		String randomUserDataUrl = "https://randomuser.me/api/";
		return webClientfirstApi
				.build()
				.get()
				.uri(randomUserDataUrl).
				retrieve().
				bodyToMono(RandomUserApiData.class) //Passing the response type
				.flatMap(randomUserApiData -> {  // Use to handle previous mono data
					UserEntity user = processUserDataFromApi(randomUserApiData);
					RandomUserApiData.Result userDetails = randomUserApiData.getResults().get(0);

					String nationalityApi = "https://api.nationalize.io/?name=" + userDetails.getName().getFirst();
					String genderApi = "https://api.genderize.io/?name=" + userDetails.getName().getFirst();

					Mono<NationalizeUserApiData> nationalizeApiMono = webCliensecondApi
							.build()
							.get()
							.uri(nationalityApi)
							.retrieve().bodyToMono(NationalizeUserApiData.class) 
							.subscribeOn(Schedulers.fromExecutor(executorService));

					Mono<GenderiseUserApiData> genderizeApiMono = webClientthirdApi
							.build()
							.get()
							.uri(genderApi)
							.retrieve().bodyToMono(GenderiseUserApiData.class)
							.subscribeOn(Schedulers.fromExecutor(executorService));

					return Mono.zip(nationalizeApiMono, genderizeApiMono, (nationalizeResponse, genderizeResponse) -> {
						userValidation.addVerificationStatus(user, nationalizeResponse, genderizeResponse);
						userRepo.save(user);
						return user;
					});
				}).doOnNext(user -> {
					System.out.println("reveived user:" + user);
				});

	}

	private UserEntity processUserDataFromApi(RandomUserApiData randomUserApiData) {
		RandomUserApiData.Result userDetails = randomUserApiData.getResults().get(0);

		// Map the UserDetails to User entity
		UserEntity user = new UserEntity();
		user.setName(userDetails.getName().getFirst() + " " + userDetails.getName().getLast());
		user.setAge(userDetails.getDob().getAge());
		user.setGender(userDetails.getGender());
		user.setDob(userDetails.getDob().getDate());
		user.setNationality(userDetails.getNat());
		user.setDateCreated(LocalDateTime.now());

		return user;
	}
}
