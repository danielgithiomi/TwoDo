package com.danielgithiomi.twodo.services.impl;

import com.danielgithiomi.twodo.domains.dtos.request.CreateUserDto;
import com.danielgithiomi.twodo.domains.dtos.response.CreatedUserDto;
import com.danielgithiomi.twodo.domains.models.Role;
import com.danielgithiomi.twodo.domains.models.User;
import com.danielgithiomi.twodo.mappers.UserMapper;
import com.danielgithiomi.twodo.repositories.RoleRepository;
import com.danielgithiomi.twodo.repositories.UserRepository;
import com.danielgithiomi.twodo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.danielgithiomi.twodo.domains.enums.UserRoles.USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreatedUserDto createNewUser(CreateUserDto createUserDto) {
        User user = userMapper.toEntity(createUserDto);

        // Password Encryption
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        // Add default role
        user.setRoles(getDefaultRoles());

        User createdUser = this.userRepository.save(user);
        return userMapper.toResponseDto(createdUser);
    }

    @Override
    public List<CreatedUserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toResponseDto).toList();
    }


    @Override
    public Optional<User> getUserById(UUID userId) {
        return Optional.empty();
    }

    private Set<Role> getDefaultRoles() {
        Set<Role> roles = new HashSet<>();
        Role defaultRole = roleRepository.findRoleByRole(USER)
                .orElseThrow(() -> new RuntimeException("Default USER Role not found"));
        roles.add(defaultRole);
        return roles;

    }
}
