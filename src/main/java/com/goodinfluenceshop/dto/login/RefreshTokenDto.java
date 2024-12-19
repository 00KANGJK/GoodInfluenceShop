package com.goodinfluenceshop.dto.login;

import com.goodinfluenceshop.domain.Admin;
import com.goodinfluenceshop.domain.RefreshToken;
import lombok.*;


public class RefreshTokenDto {
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CreateReqDto {
    private String content;
    private Admin admin;

    public RefreshToken toEntity() {
      return RefreshToken.of(content, admin);
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
