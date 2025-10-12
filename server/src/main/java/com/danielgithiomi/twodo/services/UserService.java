package com.danielgithiomi.twodo.services;

import com.danielgithiomi.twodo.domains.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User createNewUser();

    List<User> getAllUsers();

    Optional<User> getUserById(UUID userId);

}
