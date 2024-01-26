package testdemo;

public class PasswordMeter {

    public PasswordStrength meter(String pw) {
        if (pw == null || pw.isEmpty()) {
            throw new IllegalArgumentException();
        }

        boolean lengthRule = pw.length() >= 8;
        if (!lengthRule) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

}
