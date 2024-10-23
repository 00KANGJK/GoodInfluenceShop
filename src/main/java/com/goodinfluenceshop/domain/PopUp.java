package com.goodinfluenceshop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class PopUp extends BaseEntity {

  private String title; // 제목
  private String content; // 내용

  @Column(nullable = false)
  private String imageUrl; // AWS S3 이미지 URL

  @Column(nullable = false)
  private boolean isVisible; // 팝업 표시 여부

  @Column(name = "start_date")
  private LocalDateTime startDate; // 팝업 공개 시작일

  @Column(name = "end_date")
  private LocalDateTime endDate; // 팝업 공개 종료일

  public PopUp() {
  }

  public PopUp(String title, String content, String imageUrl, boolean isVisible, LocalDateTime startDate, LocalDateTime endDate) {
    this.title = title;
    this.content = content;
    this.imageUrl = imageUrl; // S3 URL로 설정
    this.isVisible = isVisible;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
