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
public class PopUpDto {

  private Long id;
  private String title; // 제목
  private String content; // 내용
  private String imageUrl; // AWS S3 이미지 URL
  private boolean isVisible; // 팝업 표시 여부
  private LocalDateTime startDate; // 팝업 공개 시작일
  private LocalDateTime endDate; // 팝업 공개 종료일
}
