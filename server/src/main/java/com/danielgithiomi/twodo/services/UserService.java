package com.danielgithiomi.twodo.services;

import com.danielgithiomi.twodo.domains.dtos.request.RegisterUserDto;
import com.danielgithiomi.twodo.domains.dtos.response.CreatedUserDto;
import com.danielgithiomi.twodo.domains.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    CreatedUserDto createNewUser(RegisterUserDto registerUserDto);

    List<CreatedUserDto> getAllUsers();

    Optional<User> getUserById(UUID userId);

}
