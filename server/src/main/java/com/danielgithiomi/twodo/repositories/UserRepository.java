package com.danielgithiomi.twodo.repositories;

import com.danielgithiomi.twodo.domains.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
