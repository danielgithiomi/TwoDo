package com.danielgithiomi.twodo.services.impl;

import com.danielgithiomi.twodo.domains.models.User;
import com.danielgithiomi.twodo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public User createNewUser() {
        return null;
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return Optional.empty();
    }
}
