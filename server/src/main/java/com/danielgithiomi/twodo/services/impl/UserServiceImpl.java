package com.danielgithiomi.twodo.services.impl;

import com.danielgithiomi.twodo.domains.models.User;
import com.danielgithiomi.twodo.repositories.UserRepository;
import com.danielgithiomi.twodo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createNewUser() {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public Optional<User> getUserById(UUID userId) {
//        return Optional.ofNullable(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        return Optional.empty();
    }
}
