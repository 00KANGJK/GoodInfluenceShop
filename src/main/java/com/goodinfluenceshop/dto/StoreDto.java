package com.goodinfluenceshop.dto;

import com.goodinfluenceshop.common.ProvideItem;
import com.goodinfluenceshop.domain.Store;
import com.goodinfluenceshop.enums.Category;
import com.goodinfluenceshop.enums.MembershipLevel;
import com.goodinfluenceshop.enums.ProvideTarget1;
import com.goodinfluenceshop.enums.ProvideTarget2;
import com.goodinfluenceshop.enums.SnsType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StoreDto {
  private int no; // 번호
  private String businessNumber; // 사업자 번호
  private MembershipLevel level; // 회원구분
  private String ceoName; // 점주 이름
  private String storeTitle; // 가게명
  private String storeEmail; // 가게 이메일
  private String phoneNumber; // 휴대폰
  private String password; // 비밀번호
  private String storePhoneNumber; // 매장 번호
  private String storeAddress; // 매장 주소
  private String storeDetailAddress; // 상세 주소
  private LocalDate enrollDate; // 신청일자
  private Boolean depositCheck; // 입금 확인
  private Boolean stickerSend; // 스티커 발송
  private Boolean kitSend; // 키트 발송
  private Boolean seeAvailable; // 노출 여부
  private Boolean opened; // 영업 상태
  private Category businessTypeBig; // 업종 대분류
  private String businessTypeMiddle; // 업종 중분류, 상위 카테고리에 따라 다른 enum 사용

  // 추가 정보
  private String openTime; // 가게 영업 시작 시간
  private String closeTime; // 가게 마감 시간
  private String openBreakTime; // 브레이크 타임 시작 시간
  private String closeBreakTime; // 브레이크 타임 마감 시간
  private List<String> holiDays; // 휴무일
  private List<ProvideItem> provideItems; // 제공 품목

  // 제공 대상
  private ProvideTarget1 provideTarget1; // 제공대상1 - 단일 선택
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


  // 로그인 요청 DTO
  @Getter
  @Setter
  public static class LoginReqDto {
    private String storeEmail;
    private String password;
  }

  // 로그인 응답 DTO
  @Builder
  @Getter
  public static class LoginResDto {
    private String accessToken;
  }

  // 회원가입 요청 DTO
  @Getter
  @Setter
  public static class SignupReqDto {
    private String storeEmail;
    private String password;

    public Store toEntity() {
      Store store = new Store();
      store.setStoreEmail(this.storeEmail);
      store.setPassword(this.password);
      return store;
    }
  }

  // 회원가입 응답 DTO
  @Builder
  @Getter
  public static class CreateResDto {
    private Integer storeAccessToken;
    private String storeEmail;
  }
}
