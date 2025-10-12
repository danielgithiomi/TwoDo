package com.danielgithiomi.twodo.mappers;

import com.danielgithiomi.twodo.domains.dtos.request.CreateUserDto;
import com.danielgithiomi.twodo.domains.models.User;

public interface UserMapper {

    User toEntity(CreateUserDto createUserDto);

    CreateUserDto toDto(User user);

}
