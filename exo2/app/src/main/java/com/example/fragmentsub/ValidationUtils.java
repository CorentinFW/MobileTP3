package com.example.fragmentsub;

import java.util.regex.Pattern;

public final class ValidationUtils {

    private static final Pattern LOGIN_PATTERN = Pattern.compile("^[A-Za-z][A-Za-z0-9_]{0,9}$");

    private ValidationUtils() {
        // Utility class
    }

    public static boolean isLoginValid(String login) {
        if (login == null || login.isEmpty()) {
            return false;
        }
        return LOGIN_PATTERN.matcher(login).matches();
    }

    public static boolean isPasswordValid(String password) {
        return password != null && password.length() == 6;
    }
}


