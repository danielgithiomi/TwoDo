package com.danielgithiomi.twodo.controllers;

import com.danielgithiomi.twodo.domains.dtos.request.CreateUserDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping
    private ResponseEntity<String> getUsers() {
        return ResponseEntity.ok("Get Users");
    }

    @PostMapping
    private ResponseEntity<String> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        log.warn("Received request to create user: {}", createUserDto);
        return new ResponseEntity<>("Created User", HttpStatus.CREATED);
    }
}
