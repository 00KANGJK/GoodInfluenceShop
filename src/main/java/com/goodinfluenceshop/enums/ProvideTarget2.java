package com.goodinfluenceshop.enums;

public enum ProvideTarget2 {
    UNDERPRIVILEGED_CHILD("결식아동"),
    FIREFIGHTER("소방관"),
    OTHER("기타");

    private final String description;

    ProvideTarget2(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
