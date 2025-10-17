package com.danielgithiomi.twodo.domains.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ApiSuccessResponse<T> {

    private T body;
    private String message;
    private HttpStatus httpStatus;

    @Builder.Default
    private int statusCode = this.httpStatus.value();

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiSuccessResponse() {
        this.timestamp = LocalDateTime.now();
    }
}
