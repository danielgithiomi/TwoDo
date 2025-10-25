package com.danielgithiomi.twodo.controllers;

import com.danielgithiomi.twodo.domains.dtos.request.RegisterUserDto;
import com.danielgithiomi.twodo.domains.dtos.response.UserResponseDto;
import com.danielgithiomi.twodo.domains.models.api.ApiSuccessResponse;
import com.danielgithiomi.twodo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiSuccessResponse<List<UserResponseDto>>> getUsers() {

        List<UserResponseDto> users = this.userService.getAllUsers();

        return ResponseEntity.status(OK).body(
                ApiSuccessResponse.<List<UserResponseDto>>builder()
                        .httpStatus(OK)
                        .message("Successfully retrieved all {" + users.size() + "} users")
                        .body(users)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiSuccessResponse<UserResponseDto>> createUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        UserResponseDto createdUser = this.userService.createNewUser(registerUserDto);
        return ResponseEntity.status(CREATED).body(
                ApiSuccessResponse.<UserResponseDto>builder()
                        .body(createdUser)
                        .message("New user created with username " + createdUser.getUsername() + " and email " + createdUser.getEmail())
                        .httpStatus(CREATED)
                        .build()
        );
    }

    @DeleteMapping("/{user_id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiSuccessResponse<UserResponseDto>> deleteUserById(@PathVariable("user_id") UUID userId) {
        UserResponseDto deletedUser = this.userService.deleteUserByUserId(userId);

        return ResponseEntity.status(OK).body(
                ApiSuccessResponse.<UserResponseDto>builder()
                        .body(deletedUser)
                        .message("User with id {" + userId + "} has been deleted successfully.")
                        .httpStatus(OK)
                        .build()
        );
    }


}
