package com.danielgithiomi.twodo.exceptions;

import lombok.Getter;

@Getter
public class JwtAuthenticationException extends RuntimeException {

    private String jwtErrorMessage;

    public JwtAuthenticationException(String message) {
        super(message);
    }

    public JwtAuthenticationException(String message, String jwtErrorMessage) {
        super(message);
        this.jwtErrorMessage = jwtErrorMessage;
    }

    public JwtAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
