package com.danielgithiomi.twodo.services.impl;

import com.danielgithiomi.twodo.domains.dtos.request.RegisterUserDto;
import com.danielgithiomi.twodo.domains.dtos.response.UserResponseDto;
import com.danielgithiomi.twodo.domains.models.Role;
import com.danielgithiomi.twodo.domains.models.User;
import com.danielgithiomi.twodo.exceptions.UserAlreadyExistsException;
import com.danielgithiomi.twodo.exceptions.UserNotFoundException;
import com.danielgithiomi.twodo.mappers.UserMapper;
import com.danielgithiomi.twodo.repositories.RoleRepository;
import com.danielgithiomi.twodo.repositories.UserRepository;
import com.danielgithiomi.twodo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.danielgithiomi.twodo.domains.enums.UserRoles.USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDto createNewUser(RegisterUserDto registerUserDto) {
        User user = userMapper.toEntity(registerUserDto);

        // Check if the user exists in the database
        if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail()))
            throw new UserAlreadyExistsException("A user with this username or email already exists in the database");

        // Password Encryption
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));

        // Add default role
        user.setRoles(getDefaultRoles());

        User createdUser = this.userRepository.save(user);
        return userMapper.toResponseDto(createdUser);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toResponseDto).toList();
    }


    @Override
    public UserResponseDto getUserById(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("No user found that matches the User-Id: {" + userId + "}")
        );

        return userMapper.toResponseDto(user);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public UserResponseDto deleteUserByUserId(UUID userId) {
        UserResponseDto userToDelete = getUserById(userId);
        this.userRepository.deleteUserByUserId(userId);

        return userToDelete;
    }


    private Set<Role> getDefaultRoles() {
        Set<Role> roles = new HashSet<>();
        Role defaultRole = roleRepository.findRoleByRole(USER)
                .orElseThrow(() -> new RuntimeException("Default USER Role not found"));
        roles.add(defaultRole);
        return roles;

    }
}
