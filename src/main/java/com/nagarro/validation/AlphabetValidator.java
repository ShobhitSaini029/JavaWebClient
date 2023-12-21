package com.nagarro.validation;

public class AlphabetValidator implements Validator {
	
	private static AlphabetValidator instance;

	public static AlphabetValidator getInstance() {
		if (instance == null) {
			instance = new AlphabetValidator();
		}
		return instance;
	}

	@Override
    public boolean isValid(String value, boolean parameter) {
        if (parameter) {
            // Validating parameters related to names and age
            return isValidNameOrAge(value);
        } else {
            // Validating parameters related to even and odd
            return isValidEvenOrOdd(value);
        }
    }

    private boolean isValidNameOrAge(String value) {
        // Validating name or age criteria
        return value.equalsIgnoreCase("name") || value.equalsIgnoreCase("age");
    }

    private boolean isValidEvenOrOdd(String value) {
        // Validating even or odd criteria
        return value.equalsIgnoreCase("even") || value.equalsIgnoreCase("odd");
    }
}
