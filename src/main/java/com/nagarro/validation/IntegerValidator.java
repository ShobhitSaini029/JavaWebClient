package com.nagarro.validation;

public class IntegerValidator implements Validator {

	private static IntegerValidator instance;

	public static IntegerValidator getInstance() {
		if (instance == null) {
			instance = new IntegerValidator();
		}
		return instance;
	}
	
	@Override
    public boolean isValid(String value, boolean parameter) {
        int val;
        try {
            val = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid integer
            return false;
        }

        if (parameter) {
            // Validating parameters for a range (1 to 5)
            return isValidRange(val, 1, 5);
        } else {
            // Validating parameters for non-negative values
            return isValidNonNegative(val);
        }
    }

    private boolean isValidRange(int value, int minValue, int maxValue) {
        return value >= minValue && value <= maxValue;
    }

    private boolean isValidNonNegative(int value) {
        return value >= 0;
    }
}
