package testdemo;

import java.util.Objects;

public class PasswordMeter {

    public PasswordStrength meter(String password) {
        if (Objects.isNull(password) || password.isBlank()) {
            return PasswordStrength.INVALID;
        }

        boolean containsUpperCase = containsUpperCase(password);
        boolean lengthEnough = lengthEnough(password);
        boolean containsNum = containsNum(password);

        if (lengthEnough && !containsUpperCase && !containsNum) {
            return PasswordStrength.WEAK;
        }

        if (!lengthEnough && !containsUpperCase && containsNum) {
            return PasswordStrength.WEAK;
        }

        if (!containsUpperCase) {
            return PasswordStrength.NORMAL;
        }

        if (!containsNum) {
            return PasswordStrength.NORMAL;
        }

        if (!lengthEnough) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private boolean lengthEnough(String password) {
        return password.length() > 8;
    }

    private boolean containsUpperCase(String password) {
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsNum(String password) {
        for (char ch : password.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

}
