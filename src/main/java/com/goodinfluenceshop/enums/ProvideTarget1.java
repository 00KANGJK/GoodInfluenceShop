package com.goodinfluenceshop.enums;

public enum ProvideTarget1 {
    CHILD_ONLY("아이 본인만"),
    WITH_ONE("동반 1인"),
    WITH_TWO("동반 2인"),
    OTHER("기타");

    private final String description;

    ProvideTarget1(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
