package com.danielgithiomi.twodo.domains.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiSuccessResponse {

    private HttpStatus httpStatus;
    private int statusCode;
    private Object body;
    private Long timestamp;
    private String message;

}
