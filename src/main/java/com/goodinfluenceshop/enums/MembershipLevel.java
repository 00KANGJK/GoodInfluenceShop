package com.goodinfluenceshop.enums;

public enum MembershipLevel {
    REGULAR_MEMBER("정회원"),
    ASSOCIATE_MEMBER("준회원");

    private final String description;

    MembershipLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
