package com.danielgithiomi.twodo.domains.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterUserDto {

    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 50, message = "First name must be between {min} and {max} characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 50, message = "First name must be between {min} and {max} characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 50, message = "Password must be between {min} and {max} characters")
    private String password;

}
