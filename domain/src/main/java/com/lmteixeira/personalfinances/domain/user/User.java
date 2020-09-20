package com.lmteixeira.personalfinances.domain.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    private String email;

    public User(String email) {
        if(!this.emailIsValid(email))
            throw new InvalidEmailException("Invalid email: " + email);
        this.email = email;
    }

    @Override
    public String toString() {
        return email;
    }

    private boolean emailIsValid(String email) {
        String emailRegex = "^(.+)@(.+)\\.(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String getEmail() {
        return this.email;
    }

    public class InvalidEmailException extends RuntimeException {
        public InvalidEmailException(String message) {
            super(message);
        }
    }
}
