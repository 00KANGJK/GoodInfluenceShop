package com.goodinfluenceshop.enums;

public enum LoginRoleType {
    ADMIN("관리자"),
    USER("사용자");

    private final String description;

    LoginRoleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
