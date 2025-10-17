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
    private HttpStatus httpStatus;
    private int statusCode;
    private LocalDateTime timestamp;
    private String message;

    public ApiSuccessResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public static class ApiSuccessResponseBuilder<T> {
        public ApiSuccessResponse<T> build() {
            ApiSuccessResponse<T> response = new ApiSuccessResponse<>();
            response.body = this.body;
            response.httpStatus = this.httpStatus;
            response.statusCode = (this.httpStatus != null) ? this.httpStatus.value() : 0;
            response.timestamp = this.timestamp != null ? this.timestamp : LocalDateTime.now();
            response.message = this.message != null ? this.message : "API call successful. Response is in the body.";
            return response;
        }
    }
}
