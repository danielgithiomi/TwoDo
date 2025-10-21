package com.danielgithiomi.twodo.domains.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "Username or Email is required to login")
        String usernameOrEmail,

        @NotBlank(message = "Password is required to login")
        @Size(min = 8, max = 50, message = "Password must be at least {min} and max {max} characters long")
        String password
) {
}
