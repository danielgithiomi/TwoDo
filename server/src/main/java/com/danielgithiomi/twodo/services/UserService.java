package com.danielgithiomi.twodo.services;

import com.danielgithiomi.twodo.domains.dtos.request.RegisterUserDto;
import com.danielgithiomi.twodo.domains.dtos.response.CreatedUserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    CreatedUserDto createNewUser(RegisterUserDto registerUserDto);

    List<CreatedUserDto> getAllUsers();

    CreatedUserDto getUserById(UUID userId);

    CreatedUserDto deleteUserByUserId(UUID userId);

}
