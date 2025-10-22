package com.danielgithiomi.twodo.controllers;

import com.danielgithiomi.twodo.domains.dtos.request.LoginRequest;
import com.danielgithiomi.twodo.domains.dtos.response.LoginResponse;
import com.danielgithiomi.twodo.domains.models.api.ApiSuccessResponse;
import com.danielgithiomi.twodo.security.interfaces.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping
    public ResponseEntity<String> getAuth() {
        return ResponseEntity.status(OK).body("Auth endpoint");
    }

    @PostMapping("/login")
    public ResponseEntity<ApiSuccessResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        HttpStatus status = OK;

        String usernameOrEmail = loginRequest.usernameOrEmail();
        String password = loginRequest.password();

        UserDetails validatedUserDetails = authService.validateUser(usernameOrEmail, password);
        String jwtToken = authService.generateJwtToken(validatedUserDetails);

        return ResponseEntity
                .status(status)
                .body(
                        ApiSuccessResponse.<LoginResponse>builder()
                                .httpStatus(status)
                                .body(
                                        LoginResponse.builder()
                                                .jwtToken(jwtToken)
                                                .dateIssued(Date.from(Instant.now()))
                                                .build()
                                )
                                .message("Login successful")
                                .build()
                );
    }

}
