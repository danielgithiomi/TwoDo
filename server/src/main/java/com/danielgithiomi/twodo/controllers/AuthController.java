package com.danielgithiomi.twodo.controllers;

import com.danielgithiomi.twodo.domains.dtos.request.LoginRequest;
import com.danielgithiomi.twodo.domains.dtos.response.LoginResponse;
import com.danielgithiomi.twodo.domains.models.api.ApiSuccessResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @GetMapping
    public ResponseEntity<String> getAuth() {
        return ResponseEntity.status(OK).body("Auth endpoint");
    }

    @PostMapping("/login")
    public ResponseEntity<ApiSuccessResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        HttpStatus status = OK;

        System.out.println("Login request received: " + loginRequest.toString());

        return ResponseEntity
                .status(status)
                .body(
                        ApiSuccessResponse.<LoginResponse>builder()
                                .httpStatus(status)
                                .body(
                                        LoginResponse.builder()
                                                .jwtToken("sfdljdfbjvld")
                                                .dateIssued(Date.from(Instant.now()))
                                                .build()
                                )
                                .message("Login successful")
                                .build()
                );
    }

}
