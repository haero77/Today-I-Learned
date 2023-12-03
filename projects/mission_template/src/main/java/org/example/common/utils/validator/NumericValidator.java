package org.example.common.utils.validator;

import java.util.regex.Pattern;

public class NumericValidator {

    private static final Pattern NUMERIC_REGEX = Pattern.compile("^[0-9]+$");

    private NumericValidator() {
    }

    public static void validateNumeric(String source) {
        if (!onlyContainsNumeric(source)) {
            throw new IllegalArgumentException(String.format("String %s contains non-numeric.", source));
        }
    }

    public static void validatePositive(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(String.format("number %d is non-positive.", number));
        }
    }

    private static boolean onlyContainsNumeric(String input) {
        return NUMERIC_REGEX.matcher(input).matches();
    }

}
