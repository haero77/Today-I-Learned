package org.example.utils;

import java.util.Objects;

public class StringValidator {

    public static void validateHasText(String source) {
        if (!hasText(source)) {
            throw new IllegalArgumentException(String.format("String '%s' doesn't have text.", source));
        }
    }
    public static void validateHasText(String literal, String message) {
        if (!hasText(literal)) {
            throw new IllegalArgumentException(message);
        }
    }

    private static boolean hasText(String source) {
        return Objects.nonNull(source) && !source.isBlank();
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
