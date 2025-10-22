package com.danielgithiomi.twodo.exceptions;

public class ValidateUserException extends RuntimeException {

    public ValidateUserException(String message) {
        super(message);
    }

    public ValidateUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
