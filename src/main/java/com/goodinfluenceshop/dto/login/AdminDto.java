package com.goodinfluenceshop.dto.login;

import com.goodinfluenceshop.domain.Admin;
import lombok.*;

@Getter
@Setter
@Builder
public class AdminDto {
  @Getter
  @Setter
  public static class LoginReqDto {
    private String email;
    private String password;
  }

  @Setter
  @Getter
  @Builder
  public static class CreateReqDto {
    private String email;
    private String password;
    private String name;

    public Admin toEntity() {
      return Admin.builder()
        .email(email)
        .password(password)
        .name(name)
        .build();
    }
  }
  @Builder
  public static class CreateResDto {
    private String id;
  }

  @Builder
  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class SignupReqDto{
    private String email;
    private String password;
  }


}
