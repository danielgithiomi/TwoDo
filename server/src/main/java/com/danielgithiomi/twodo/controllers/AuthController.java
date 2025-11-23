package com.danielgithiomi.twodo.controllers;

import com.danielgithiomi.twodo.domains.dtos.request.LoginRequest;
import com.danielgithiomi.twodo.domains.dtos.response.LoginResponse;
import com.danielgithiomi.twodo.domains.dtos.response.UserResponseDto;
import com.danielgithiomi.twodo.domains.models.api.ApiSuccessResponse;
import com.danielgithiomi.twodo.security.AuthUser;
import com.danielgithiomi.twodo.security.interfaces.AuthService;
import com.danielgithiomi.twodo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiSuccessResponse<UserResponseDto>> getAuth(Authentication authentication) {

        if (authentication == null)
            throw new IllegalStateException("No authenticated user found. Please login first to access this resource.");

        AuthUser authenticatedUser = (AuthUser) authentication.getPrincipal();
        UserResponseDto userResponseDto = this.userService.getUserById(authenticatedUser.getUserId());

        HttpStatus statusCode = OK;

        return ResponseEntity.status(statusCode).body(
                ApiSuccessResponse.<UserResponseDto>builder()
                        .httpStatus(statusCode)
                        .body(userResponseDto)
                        .message("Logged in details retrieved successfully for user: " + authenticatedUser.getUsername())
                        .build()
        );
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
