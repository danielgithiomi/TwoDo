package com.danielgithiomi.twodo.domains.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRoles {

    USER("user"),
    ADMIN("admin");

    private final String role;

    public static UserRoles fromString(String role) {
        return UserRoles.valueOf(role.toUpperCase());
    }

}
