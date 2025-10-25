package com.danielgithiomi.twodo.exceptions.handlers;

import com.danielgithiomi.twodo.domains.models.api.ApiErrorResponse;
import com.danielgithiomi.twodo.exceptions.JwtAuthenticationException;
import com.danielgithiomi.twodo.exceptions.ValidateUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@ControllerAdvice
public class AuthenticationExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class) // Thrown by Spring Security after UsernameNotFoundException
    public ResponseEntity<ApiErrorResponse> badCredentialsException(BadCredentialsException ex) {

        HttpStatus status = BAD_REQUEST;
        log.error("BadCredentialsException: {}", ex.getMessage());

        return ResponseEntity.status(status).body(
                ApiErrorResponse.builder()
                        .statusCode(status.value())
                        .message("Invalid login credentials. Please check and try again.")
                        .build()
        );
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiErrorResponse> authorizationDeniedException(AuthorizationDeniedException ex) {

        HttpStatus status = FORBIDDEN;
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

        HttpStatus status = UNAUTHORIZED;
        log.error("ValidateUserException: {}", ex.getMessage());

        return ResponseEntity.status(status).body(
                ApiErrorResponse.builder()
                        .statusCode(status.value())
                        .message(ex.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<ApiErrorResponse> jWTAuthenticationException(JwtAuthenticationException ex) {

        HttpStatus status = FORBIDDEN;
        log.error("JwtAuthenticationException: {}", ex.getMessage(), ex);

        List<ApiErrorResponse.FieldError> errors = new ArrayList<>();
        if (ex.getJwtErrorMessage() != null) {
            errors.add(ApiErrorResponse.FieldError.builder()
                    .fieldName("JWT")
                    .fieldMessage(ex.getJwtErrorMessage())
                    .build());
        } else {
            errors = List.of();
        }

        return ResponseEntity.status(status).body(
                ApiErrorResponse.builder()
                        .statusCode(status.value())
                        .message(ex.getMessage())
                        .errors(errors)
                        .build()
        );
    }
}
