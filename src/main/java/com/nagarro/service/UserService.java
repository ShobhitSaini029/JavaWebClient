package com.nagarro.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.entities.UserEntity;
import com.nagarro.repository.UserRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

	@Autowired
	private ApiCallingService apiClient;
	
	@Autowired
	UserRepo userRepo;

	@PersistenceContext
    private EntityManager entityManager;
	
	public Mono<List<UserEntity>> saveUser(int size) {
		Flux<Mono<UserEntity>> userMonos = Flux.range(0, size).map(i -> apiClient.fetchUserDataFromApi());

		return Flux.merge(userMonos).collectList();
	}
	
	// to fetch all the users user from db
		public List<UserEntity> getAllUsers() {
			List<UserEntity> userList = userRepo.findAll();
			
			return userList;
		}
		
		// to fetch the count of users
		public int getUserCount() {
			return (int) userRepo.count();
		}
		
		
		//only for test, might delete later
		public List<UserEntity> getUsers(int limit, int offset){

			TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class);

		        query.setFirstResult(offset);
		        query.setMaxResults(limit);
		        
		        return query.getResultList();
		}
}
