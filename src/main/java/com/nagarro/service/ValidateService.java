package com.nagarro.service;

import org.springframework.stereotype.Service;

import com.nagarro.validation.Validator;

@Service
public class ValidateService {

	
	public void validateParameter(String value, Validator validator, boolean parameter) {
		if(!validator.isValid(value,parameter)) {
			//throw customised error 
			
			throw new IllegalArgumentException("Invalid parameter value" + value);
		}
	}
}
