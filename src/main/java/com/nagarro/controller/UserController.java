package com.nagarro.controller;

import java.util.List;

//import javax.xml.bind.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.apiData.ApiData;
import com.nagarro.entities.UserEntity;
import com.nagarro.service.GenApiSerive;
import com.nagarro.service.SortingService;
import com.nagarro.service.UserService;
import com.nagarro.validation.Validator;
import com.nagarro.validation.ValidatorFactory;
import com.nagarro.service.ValidateService;

import reactor.core.publisher.Mono;

@RestController
//@RequestMapping
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ValidateService validateService;
	
	@Autowired
	SortingService sortingService;
	
	@Autowired
	GenApiSerive genApiService;
	
	@PostMapping("/user")
	public Mono<ResponseEntity<List<UserEntity>>> createUser(@RequestParam("size") int size) {
//		System.out.println("hello");
		if (size < 1 || size > 5) {
			return Mono.just(ResponseEntity.badRequest().build());
		}

		return userService.saveUser(size).map(users -> ResponseEntity.ok(users));
	}
	
	@GetMapping("/user")
	public ResponseEntity<ApiData> getUsers(@RequestParam(name = "sortType") String sortType,
			@RequestParam(name = "sortOrder") String sortOrder, @RequestParam(name = "limit") String limit,
			@RequestParam(name = "offset") String offset) throws IllegalAccessException {

		if (sortType.matches("\\d+") || sortOrder.matches("\\d+")) {
			throw new IllegalAccessException();
		}

		// creating validators using validator factory
		Validator sortTypeValidator = ValidatorFactory.createValidator(sortType);
		Validator sortOrderValidator = ValidatorFactory.createValidator(sortOrder);
		Validator limitValidator = ValidatorFactory.createValidator(limit);
		Validator offsetValidator = ValidatorFactory.createValidator(offset);

		// validating the parameters
		validateService.validateParameter(sortType, sortTypeValidator, true);
		validateService.validateParameter(sortOrder, sortOrderValidator, false);
		validateService.validateParameter(limit, limitValidator, true);
		validateService.validateParameter(offset, offsetValidator, false);

		// get the users from database according to limit and offset
		List<UserEntity> userList = userService.getUsers(Integer.parseInt(limit), Integer.parseInt(offset));
		int userCount = userService.getUserCount();

		// applying sorting to the data retreieved from above

		if (Integer.parseInt(offset) < userCount) {

			if (sortType.equalsIgnoreCase("age")) {
				if (sortOrder.equalsIgnoreCase("even")) {
					sortingService.sortByAgeEven(userList);
				} else {
					sortingService.sortByAgeOdd(userList);
				}
			} else if (sortType.equalsIgnoreCase("name")) {
				if (sortOrder.equalsIgnoreCase("even")) {
					sortingService.sortByNameEven(userList);
				} else {
					sortingService.sortByNameOdd(userList);
				}
			}

			// sending the list to create the api response
			ApiData response = genApiService.constructApiResponse(userList, Integer.parseInt(limit),
					Integer.parseInt(offset));

			// sending the list+pageinfo as api response
			return ResponseEntity.ok(response);

		} else {
			throw new IllegalArgumentException();
		}

	}
}
