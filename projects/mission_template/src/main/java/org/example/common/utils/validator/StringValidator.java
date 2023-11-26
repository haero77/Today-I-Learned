package org.example.common.utils.validator;

import java.util.Objects;

public class StringValidator {

    public static void validateNotEmpty(String literal, String message) {
        if (!hasContents(literal)) {
            throw new IllegalArgumentException(message);
        }
    }

    private static boolean hasContents(String literal) {
        if (Objects.isNull(literal)) {
            return false;
        }
        if (literal.isEmpty()) {
            return false;
        }
        return !literal.isBlank();
    }

    private static boolean isInteger(String literal) {
        try {
            Integer.parseInt(literal);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
