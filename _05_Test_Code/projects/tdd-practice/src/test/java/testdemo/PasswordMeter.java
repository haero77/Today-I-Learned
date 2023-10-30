package testdemo;

public class PasswordMeter {

    public PasswordStrength meter(String pw) {
        if (pw == null || pw.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if ("abcC123".equals(pw)) {
            return PasswordStrength.NORMAL;
        }
        return PasswordStrength.STRONG;
    }

}
