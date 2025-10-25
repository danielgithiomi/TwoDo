package com.danielgithiomi.twodo.services;

import com.danielgithiomi.twodo.domains.dtos.request.RegisterUserDto;
import com.danielgithiomi.twodo.domains.dtos.response.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto createNewUser(RegisterUserDto registerUserDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(UUID userId);

    UserResponseDto deleteUserByUserId(UUID userId);

}
