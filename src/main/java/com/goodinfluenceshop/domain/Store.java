package com.goodinfluenceshop.domain;

import com.goodinfluenceshop.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Store extends StoreBaseEntity {

    private String ceoName; // 점주 이름
    private String storeEmail; // 가게 이메일
    private String phoneNumber; // 휴대폰
    private String password; // 비밀번호
    private String storePhoneNumber; // 매장 번호
    private String storeAddress; // 매장 주소
    private String storeDetailAddress; // 상세 주소

    private Boolean opened; // 영업 상태
    private String businessNumber; // 사업자 번호

    // 추가 정보
    private String openTime; // 가게 영업 시작 시간
    private String closeTime; // 가게 마감 시간
    private String openBreakTime; // 브레이크 타임 시작 시간
    private String closeBreakTime; // 브레이크 타임 마감 시간

    @ElementCollection
    private List<String> holiDays; // 휴무일

    // 제공 대상
    @Enumerated(EnumType.STRING)
    private ProvideTarget1 provideTarget1; // 제공대상1 - 단일 선택

    @ElementCollection(targetClass = ProvideTarget2.class)
    @Enumerated(EnumType.STRING)
    private List<ProvideTarget2> provideTarget2; // 제공대상2 - 다중 선택 가능

    // SNS 관련
    private SnsType snsType1; // SNS 타입 1
    private String snsType1Url; // SNS 계정명 1
    private SnsType snsType2; // SNS 타입 2
    private String snsType2Url; // SNS 계정명 2

    // 이미지
    private String storeImgCI; // 상호명 이미지
    private String storeImgFront; // 가게 전면 이미지
    private String storeImgInside; // 가게 내부 이미지
    private String storeImgMenupan; // 메뉴판 이미지
    private String storeImgMenu; // 대표 메뉴 이미지

    // 내부 클래스 예시 (제공 품목)
    @ElementCollection
    private List<com.goodinfluenceshop.common.ProvideItem> provideItems = new ArrayList<com.goodinfluenceshop.common.ProvideItem>(); // 초기화 필요

    // ProvideItems 수동 설정 메서드 추가
//    public void setProvideItems(List<ProvideItem> provideItems) {
//
//    }

    public void setProvideItems(List<com.goodinfluenceshop.common.ProvideItem> provideItems) {
        this.provideItems.clear();
        if (provideItems != null) {
            this.provideItems.addAll(provideItems);
        }
    }


    @Embeddable
    public static class ProvideItem {
        private String name; // 품목 이름
        private int existingPrice; // 기존 가격
        private int providePrice; // 제공 가격
        private boolean freeProvide; // 무료 제공 여부
    }

    // 기본 생성자 및 파라미터 생성자
    public Store() {
    }

    public Store(MembershipLevel level, String storeTitle, LocalDate enrollDate, Boolean depositCheck, Boolean stickerSend, Boolean kitSend, Boolean seeAvailable, Category businessTypeBig, Enum<?> businessTypeMiddle, String businessNumber, Boolean opened, String ceoName, String storeEmail, String phoneNumber, String password, String storePhoneNumber, String storeAddress, String storeDetailAddress, String openTime, String closeTime, String openBreakTime, String closeBreakTime, List<String> holiDays, ProvideTarget1 provideTarget1, List<ProvideTarget2> provideTarget2, SnsType snsType1, String snsType1Url, SnsType snsType2, String snsType2Url, String storeImgCI, String storeImgFront, String storeImgInside, String storeImgMenupan, String storeImgMenu, List<com.goodinfluenceshop.common.ProvideItem> provideItems) {
        this.setLevel(level);
        this.setStoreTitle(storeTitle);
        this.setEnrollDate(enrollDate);
        this.setDepositCheck(depositCheck);
        this.setStickerSend(stickerSend);
        this.setKitSend(kitSend);
        this.setSeeAvailable(seeAvailable);
        this.setBusinessTypeBig(businessTypeBig);
        this.setBusinessTypeMiddle(businessTypeBig, businessTypeMiddle);
        this.businessNumber = businessNumber;
        this.opened = opened;
        this.ceoName = ceoName;
        this.storeEmail = storeEmail;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.storePhoneNumber = storePhoneNumber;
        this.storeAddress = storeAddress;
        this.storeDetailAddress = storeDetailAddress;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.openBreakTime = openBreakTime;
        this.closeBreakTime = closeBreakTime;
        this.holiDays = holiDays;
        this.provideTarget1 = provideTarget1;
        this.provideTarget2 = provideTarget2;
        this.snsType1 = snsType1;
        this.snsType1Url = snsType1Url;
        this.snsType2 = snsType2;
        this.snsType2Url = snsType2Url;
        this.storeImgCI = storeImgCI;
        this.storeImgFront = storeImgFront;
        this.storeImgInside = storeImgInside;
        this.storeImgMenupan = storeImgMenupan;
        this.storeImgMenu = storeImgMenu;
        this.provideItems = provideItems;
    }
}
