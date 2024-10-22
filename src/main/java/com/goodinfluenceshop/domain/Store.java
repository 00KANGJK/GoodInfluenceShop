package com.goodinfluenceshop.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Store extends StoreBaseEntity{

    private String level; // 회원구분
    private String storeTitle; // 가게명
    private LocalDateTime enrollDate; // 신청일자
    private Boolean depositCheck; // 입금확인
    private Boolean stickerSend; // 스티커 발송
    private Boolean kitSend; // 키트 발송
    private Boolean seeAvailable; // 노출여부
    private String businessType; // 업종
    public Store() {
    }

    public Store(String level, String storeTitle, LocalDateTime enrollDate, Boolean depositCheck, Boolean stickerSend, Boolean kitSend, Boolean seeAvailable, String businessType) {
        this.level = level;
        this.storeTitle = storeTitle;
        this.enrollDate = enrollDate;
        this.depositCheck = depositCheck;
        this.stickerSend = stickerSend;
        this.kitSend = kitSend;
        this.seeAvailable = seeAvailable;
        this.businessType = businessType;
    }
}
