package ua.test.utils;

import java.util.regex.Pattern;

public class Validation {
    public static final String EMAIL_PATTERN = "(\\w{4,})@(\\w+\\.)([a-zA-z]{2,4})";
    public static final int MAX_TEST_TIME = 90;
    public static final int MAX_TEST_SIZE = 100;

    private Validation() {}

    public static boolean isEmailValid(String email) {
        return email.matches(EMAIL_PATTERN);
    }

    public static boolean isStringValid(String text) {
        return false;
    }

    public static boolean isTestDurationValid(int duration) {
        return (duration > 0 && duration <= MAX_TEST_TIME);
    }

    public static boolean isTestSizeValid(int size) {
        return (size > 0 && size < MAX_TEST_SIZE);
    }
}
