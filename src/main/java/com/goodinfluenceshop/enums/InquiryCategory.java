package com.goodinfluenceshop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InquiryCategory {
    APPLY("선한가게신청"), DONATE("후원"), STUDENT("학생"), MEMBER("회원정보"), ETC("기타");

    private final String kor;

    public static InquiryCategory from(String kor) {
        for (InquiryCategory inquiryCategory : InquiryCategory.values()) {
            if (inquiryCategory.getKor().equals(kor)) {
                return inquiryCategory;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 문의 카테고리입니다.");
    }
}
