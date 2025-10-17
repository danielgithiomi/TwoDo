package com.danielgithiomi.twodo.domains.models;

import com.danielgithiomi.twodo.domains.enums.UserRoles;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(
        name = "roles",
        uniqueConstraints = @UniqueConstraint(columnNames = {"role"})
)
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID roleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private UserRoles role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role roles = (Role) o;
        return Objects.equals(roleId, roles.roleId) && Objects.equals(role, roles.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, role);
    }
}
