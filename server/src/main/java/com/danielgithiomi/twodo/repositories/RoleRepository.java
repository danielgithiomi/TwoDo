package com.danielgithiomi.twodo.repositories;

import com.danielgithiomi.twodo.domains.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
