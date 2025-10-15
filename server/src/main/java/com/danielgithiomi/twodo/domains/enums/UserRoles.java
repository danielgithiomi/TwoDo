package com.danielgithiomi.twodo.domains.enums;

public enum UserRoles {

    USER("user"),
    ADMIN("admin");

    private final String role;

    UserRoles(String role) {
        this.role = role;
    }

    public static UserRoles fromString(String role) {
        return UserRoles.valueOf(role.toUpperCase());
    }

    public String getRole() {
        return role;
    }

}
