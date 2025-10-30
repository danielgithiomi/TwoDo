package com.danielgithiomi.twodo.controllers;

import com.danielgithiomi.twodo.domains.enums.Gender;
import com.danielgithiomi.twodo.domains.models.api.ApiSuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1")
public class GenderController {

    @GetMapping("/genders")
    public ResponseEntity<ApiSuccessResponse<List<String>>> getGenders() {
        List<String> genders = Arrays.stream(Gender.values())
                .map(Gender::getGenderName)
                .toList();

        HttpStatus status = OK;

        return ResponseEntity.status(status)
                .body(
                        ApiSuccessResponse.<List<String>>builder()
                                .httpStatus(status)
                                .body(genders)
                                .message("Successfully retrieved all genders")
                                .build()
                );
    }

}
