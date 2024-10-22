package com.goodinfluenceshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
     int no; // 번호
     String level; // 회원구분
     String storeTitle; // 가게명
     LocalDateTime enrollDate; // 신청일자
     Boolean depositCheck; // 입금확인
     Boolean stickerSend; // 스티커 발송
     Boolean kitSend; // 키트 발송
     Boolean seeAvailable; // 노출여부
     String businessType; // 업종
}