package com.nagarro.validation;


public class ValidatorFactory {
	
	public static Validator createValidator(String value) {

		if (value.matches("\\d+")) {
			return IntegerValidator.getInstance();
		} else {
			return AlphabetValidator.getInstance();
		}
	}

}
