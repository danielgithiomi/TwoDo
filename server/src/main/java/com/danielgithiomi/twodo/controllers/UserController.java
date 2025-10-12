package com.danielgithiomi.twodo.controllers;

import com.danielgithiomi.twodo.domains.dtos.request.CreateUserDto;
import com.danielgithiomi.twodo.domains.models.User;
import com.danielgithiomi.twodo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    private ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    private ResponseEntity<String> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        log.warn("Received request to create user: {}", createUserDto);
        return new ResponseEntity<>("Created User", HttpStatus.CREATED);
    }
}
