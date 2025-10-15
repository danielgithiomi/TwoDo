package com.danielgithiomi.twodo.domains.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@Table(
        name = "roles",
        uniqueConstraints = @UniqueConstraint(columnNames = {"role"})
)
@AllArgsConstructor
@RequiredArgsConstructor
public class Roles {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID roleId;

    private String role;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return Objects.equals(roleId, roles.roleId) && Objects.equals(role, roles.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, role);
    }
}
