package com.goodinfluenceshop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnnouncementCategory {
    NEWS("뉴스"), ANNOUNCEMENT("공지");
    private final String kor;

    public static AnnouncementCategory from(String kor) {
        for (AnnouncementCategory announcementCategory : AnnouncementCategory.values()) {
            if (announcementCategory.getKor().equals(kor)) {
                return announcementCategory;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 공지 카테고리입니다.");
    }
}
