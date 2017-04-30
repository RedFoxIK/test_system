package ua.test.services;

import java.util.regex.Pattern;

public class Validation {
    private static Pattern emailPattern = Pattern.compile("(\\w{4,})@(\\w+\\.)([a-z]{2,4})");

    Validation() {}


}
