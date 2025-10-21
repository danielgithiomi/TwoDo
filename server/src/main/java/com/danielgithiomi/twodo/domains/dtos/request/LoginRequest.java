package com.danielgithiomi.twodo.domains.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Username or Email is required to login")
        String usernameOrEmail,

        @NotBlank(message = "Password is required to login")
//        @Size(min = 0, max = 8, message = "Password must be at least {min} and max {max} characters long")
        String password
) {
}
