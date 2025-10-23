package com.danielgithiomi.twodo.exceptions.handlers;

import com.danielgithiomi.twodo.domains.models.api.ApiErrorResponse;
import com.danielgithiomi.twodo.exceptions.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
