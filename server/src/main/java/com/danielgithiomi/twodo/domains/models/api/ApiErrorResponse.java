package com.danielgithiomi.twodo.domains.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {

    private int errorStatusCode;

    private List<FieldError> errors;

    @Builder.Default
    private String message = "An unexpected error occurred";

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FieldError {
        private String fieldName;
        private String fieldMessage;
    }

}
