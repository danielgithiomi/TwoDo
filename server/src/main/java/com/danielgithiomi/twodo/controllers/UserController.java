package com.danielgithiomi.twodo.controllers;

import com.danielgithiomi.twodo.domains.dtos.request.RegisterUserDto;
import com.danielgithiomi.twodo.domains.dtos.response.CreatedUserDto;
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
    private ResponseEntity<List<CreatedUserDto>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping
    private ResponseEntity<CreatedUserDto> createUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        CreatedUserDto createdUser = this.userService.createNewUser(registerUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


}
