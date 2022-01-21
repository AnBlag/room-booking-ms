package ru.roombooking.front.config.security;

public enum Permission {
    USER_READ("user:read"),
    USER_CREATE("user:create"),
    USER_DELETE("user:delete"),
    USER_UPDATE("user:update"),
    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}