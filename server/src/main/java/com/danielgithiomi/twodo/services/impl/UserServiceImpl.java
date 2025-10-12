package com.danielgithiomi.twodo.services.impl;

import com.danielgithiomi.twodo.domains.dtos.request.CreateUserDto;
import com.danielgithiomi.twodo.domains.dtos.response.CreatedUserDto;
import com.danielgithiomi.twodo.domains.models.User;
import com.danielgithiomi.twodo.mappers.UserMapper;
import com.danielgithiomi.twodo.repositories.UserRepository;
import com.danielgithiomi.twodo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreatedUserDto createNewUser(CreateUserDto createUserDto) {
        User user = userMapper.toEntity(createUserDto);

        // Password Encryption
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        User createdUser = this.userRepository.save(user);
        return userMapper.toResponseDto(createdUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public Optional<User> getUserById(UUID userId) {
        return Optional.empty();
    }
}
