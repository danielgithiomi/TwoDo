package com.danielgithiomi.twodo.mappers.impl;

import com.danielgithiomi.twodo.domains.dtos.request.CreateUserDto;
import com.danielgithiomi.twodo.domains.models.User;
import com.danielgithiomi.twodo.mappers.UserMapper;

public class UserMapperImpl implements UserMapper {
    @Override
    public User toEntity(CreateUserDto createUserDto) {
        return null;
    }

    @Override
    public CreateUserDto toDto(User user) {
        return null;
    }
}
