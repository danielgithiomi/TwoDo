package com.danielgithiomi.twodo.services;

import com.danielgithiomi.twodo.domains.models.User;

import java.util.Optional;

public interface UserService {

    User createNewUser();

    Optional<User> getUserById(Long userId);

}
