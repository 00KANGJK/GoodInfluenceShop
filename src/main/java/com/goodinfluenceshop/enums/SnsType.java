package com.goodinfluenceshop.enums;

public enum SnsType {
    INSTA("Instagram"),
    KAKAO("KakaoTalk"),
    YOUTUBE("YouTube"),
    TWITTER("Twitter"),
    BAND("Band"),
    NBLOG("Naver Blog"),
    ETC("Other");

    private final String description;

    SnsType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
