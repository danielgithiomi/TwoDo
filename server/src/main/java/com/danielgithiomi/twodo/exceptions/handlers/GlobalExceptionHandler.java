package com.danielgithiomi.twodo.exceptions.handlers;

import com.danielgithiomi.twodo.domains.models.api.ApiErrorResponse;
import com.danielgithiomi.twodo.exceptions.UserAlreadyExistsException;
import com.danielgithiomi.twodo.exceptions.ValidateUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> userAlreadyExistsException(UserAlreadyExistsException ex) {

        HttpStatus status = CONFLICT;

        return ResponseEntity.status(status).body(
                ApiErrorResponse.builder()
                        .statusCode(status.value())
                        .message(ex.getMessage())
                        .build());

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> illegalArgumentException(IllegalArgumentException ex) {

        HttpStatus status = BAD_REQUEST;

        return ResponseEntity.status(status).body(
                ApiErrorResponse.builder()
                        .statusCode(status.value())
                        .message(ex.getMessage())
                        .build());

    }

    @ExceptionHandler(BadCredentialsException.class) // Thrown by Spring Security after UsernameNotFoundException
    public ResponseEntity<ApiErrorResponse> badCredentialsException(BadCredentialsException ex) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        log.error("BadCredentialsException: {}", ex.getMessage());

        return ResponseEntity.status(status).body(
                ApiErrorResponse.builder()
                        .statusCode(status.value())
                        .message(ex.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiErrorResponse> authorizationDeniedException(AuthorizationDeniedException ex) {

        HttpStatus status = HttpStatus.FORBIDDEN;
        log.error("AuthorizationDeniedException: {}", ex.getMessage());

        return ResponseEntity.status(status).body(
                ApiErrorResponse.builder()
                        .statusCode(status.value())
                        .message(ex.getMessage() + "! You do not have permission to perform this action.")
                        .build()
        );
    }

    @ExceptionHandler(ValidateUserException.class)
    public ResponseEntity<ApiErrorResponse> validateUserException(ValidateUserException ex) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        log.error("ValidateUserException: {}", ex.getMessage());

        return ResponseEntity.status(status).body(
                ApiErrorResponse.builder()
                        .statusCode(status.value())
                        .message(ex.getMessage())
                        .build()
        );
    }
}
