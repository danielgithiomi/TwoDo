package com.danielgithiomi.twodo.exceptions;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends RuntimeException {

    private final int errorCode;

    public UserAlreadyExistsException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public UserAlreadyExistsException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

}
