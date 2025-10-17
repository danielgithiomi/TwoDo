package com.danielgithiomi.twodo.controllers;

import com.danielgithiomi.twodo.domains.dtos.request.RegisterUserDto;
import com.danielgithiomi.twodo.domains.dtos.response.CreatedUserDto;
import com.danielgithiomi.twodo.domains.models.api.ApiSuccessResponse;
import com.danielgithiomi.twodo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    private ResponseEntity<ApiSuccessResponse<List<CreatedUserDto>>> getUsers() {

        return ResponseEntity.status(OK).body(
                ApiSuccessResponse.<List<CreatedUserDto>>builder()
                        .httpStatus(OK)
                        .message("Successfully retrieved all users")
                        .body(this.userService.getAllUsers())
                        .build()
        );
    }

    @PostMapping
    private ResponseEntity<ApiSuccessResponse<CreatedUserDto>> createUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        CreatedUserDto createdUser = this.userService.createNewUser(registerUserDto);
        return ResponseEntity.status(CREATED).body(
                ApiSuccessResponse.<CreatedUserDto>builder()
                        .body(createdUser)
                        .message("New user created with username " + createdUser.getUsername() + " and email " + createdUser.getEmail())
                        .httpStatus(CREATED)
                        .build()
        );
    }


}
