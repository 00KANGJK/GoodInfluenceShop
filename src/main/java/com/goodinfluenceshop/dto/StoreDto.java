package com.goodinfluenceshop.dto;

import com.goodinfluenceshop.common.ProvideItem;
import com.goodinfluenceshop.enums.MembershipLevel;
import com.goodinfluenceshop.enums.ProvideTarget1;
import com.goodinfluenceshop.enums.ProvideTarget2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
     int no; // 번호
     String businessNumber; // 사업자 번호
     MembershipLevel level; // 회원구분
     String ceoName; // 점주 이름
     String storeTitle; // 가게명
     String storeEmail; // 가게 이메일
     String phoneNumber; // 휴대폰
     String password; // 비밀번호
     String storePhoneNumber; // 매장 번호
     String storeAddress; // 매장 주소
     String storeDetailAddress; // 상세 주소
     String enrollDate; // 신청일자
     Boolean depositCheck; // 입금 확인
     Boolean stickerSend; // 스티커 발송
     Boolean kitSend; // 키트 발송
     Boolean seeAvailable; // 노출 여부
     Boolean opened; // 영업 상태
     String businessType; // 업종

     // 추가 정보
     LocalDateTime openTime; // 가게 영업 시작 시간
     LocalDateTime closeTime; // 가게 마감 시간
     LocalDateTime openBreakTime; // 브레이크 타임 시작 시간
     LocalDateTime closeBreakTime; // 브레이크 타임 마감 시간
     List<String> restDays; // 휴무일
     private List<ProvideItem> provideItems; // 제공 품목

     // 제공 대상
     ProvideTarget1 provideTarget1; // 제공대상1 - 단일 선택
     List<ProvideTarget2> provideTarget2; // 제공대상2 - 다중 선택 가능

     // SNS 관련
     String snsType1; // SNS 타입 1
     String snsName1; // SNS 계정명 1
     String snsType2; // SNS 타입 2
     String snsName2; // SNS 계정명 2

     // 이미지
     String storeImgCI; // 상호명 이미지
     String storeImgFront; // 가게 전면 이미지
     String storeImgInside; // 가게 내부 이미지
     String storeImgMenupan; // 메뉴판 이미지
     String storeImgMenu; // 대표 메뉴 이미지
}
