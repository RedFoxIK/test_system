package ua.test.utils;

import java.util.regex.Pattern;

public class Validation {
    Validation() {}

    public boolean isEmailValid(String email) {
        return email.matches("(\\w{4,})@(\\w+\\.)([a-zA-z]{2,4})");
    }

    public boolean isStringValid(String string) {
        return false;
    }
}
