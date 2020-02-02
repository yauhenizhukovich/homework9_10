package com.gmail.supersonicleader.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static final int MIN_SYMBOLS_USERNAME = 3;
    private static final int MAX_SYMBOLS_USERNAME = 40;
    private static final int MIN_SYMBOLS_PASSWORD = 8;
    private static final int MAX_SYMBOLS_PASSWORD = 40;
    private static final int MAX_AGE = 120;
    private static final int MIN_SYMBOLS_ADDRESS = 3;
    private static final int MAX_SYMBOLS_ADDRESS = 100;
    private static final int SYMBOLS_TELEPHONE = 9;
    private static final String USERNAME_REGEX = "^[A-Za-z0-9_-]+$";
    private static final String ADDRESS_REGEX = "^[A-Za-z0-9'.\\-\\s,]+$";
    private static final String PASSWORD_REGEX = "^[A-Za-z0-9]+$";
    private static final String TELEPHONE_REGEX = "^((\\+375|80)+([0-9]){" + SYMBOLS_TELEPHONE + "})$";

    public static void checkUsername(String username) throws IllegalArgumentException {
        if (username.length() < MIN_SYMBOLS_USERNAME || username.length() > MAX_SYMBOLS_USERNAME) {
            throw new IllegalArgumentException(
                    "Username length should be from " + MIN_SYMBOLS_USERNAME + " to " + MAX_SYMBOLS_USERNAME + "."
            );
        }
        Pattern pattern = Pattern.compile(USERNAME_REGEX);
        Matcher matcher = pattern.matcher(username);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Username can contain letters, digits and \"_\", \"-\" characters.");
        }
    }

    public static void checkPassword(String password) throws IllegalArgumentException {
        if (password.length() < MIN_SYMBOLS_PASSWORD || password.length() > MAX_SYMBOLS_PASSWORD) {
            throw new IllegalArgumentException(
                    "Password length should be from " + MIN_SYMBOLS_PASSWORD + " to " + MAX_SYMBOLS_PASSWORD + "."
            );
        }
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Password can contain only letters and digits.");
        }
    }

    public static void checkActivity(String isActive) throws IllegalArgumentException {
        Boolean isActiveBool = Boolean.parseBoolean(isActive);
        if (!isActiveBool && !isActive.equals("false")) {
            throw new IllegalArgumentException("You should enter \"true\" or \"false\" in activity field.");
        }
    }

    public static void checkAge(String age) throws NumberFormatException {
        try {
            Integer ageInt = Integer.parseInt(age);
            if (ageInt <= 0) {
                throw new NumberFormatException("Age cannot be zero or a negative number.");
            }
            if (ageInt > MAX_AGE) {
                throw new NumberFormatException("We doubt that you are of such age.");
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Age can only contain numbers.");
        }
    }

    public static void checkAddress(String address) throws IllegalArgumentException {
        if (address.length() < MIN_SYMBOLS_ADDRESS || address.length() > MAX_SYMBOLS_ADDRESS) {
            throw new IllegalArgumentException(
                    "Address length should be from " + MIN_SYMBOLS_ADDRESS + " to " + MAX_SYMBOLS_ADDRESS + "."
            );
        }
        Pattern pattern = Pattern.compile(ADDRESS_REGEX);
        Matcher matcher = pattern.matcher(address);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Address can contain letters, digits, spaces and \".-,\" characters.");
        }
    }

    public static void checkTelephone(String telephone) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile(TELEPHONE_REGEX);
        Matcher matcher = pattern.matcher(telephone);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "Telephone number must begin with \"+375\" or \"80\" followed by " + SYMBOLS_TELEPHONE + " digits.");
        }
    }

}
