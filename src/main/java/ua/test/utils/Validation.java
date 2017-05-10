package ua.test.utils;

import java.util.regex.Pattern;

public class Validation {
    public static final int MAX_TEST_TIME = 90;
    public static final int MAX_TEST_SIZE = 100;
    public static final String PASSWORD_PATTERN = "/S{5,10}";
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Validation() {}

    public static boolean isEmailValid(String email) {
        return email.matches(EMAIL_PATTERN);
    }

    public static boolean isEmtyText(String text) {
        return "".equals(text);
    }

    public static boolean isTestDurationValid(int duration) {
        return (duration > 0 && duration <= MAX_TEST_TIME);
    }

    public static boolean isTestSizeValid(int size) {
        return (size > 0 && size < MAX_TEST_SIZE);
    }
}
