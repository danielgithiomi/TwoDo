package com.danielgithiomi.twodo.exceptions;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(int errorCode, String message, Throwable cause) {
        super(message, cause);
    }

}
