package com.goodinfluenceshop.dto.login;

import com.goodinfluenceshop.domain.RefreshToken;
import lombok.*;


public class RefreshTokenDto {
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CreateReqDto {
    private String content;
    private String adminId;

    public RefreshToken toEntity() {
      return RefreshToken.of(content, adminId);
    }
  }
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class CreateResDto {
    private Long id;
  }
}
