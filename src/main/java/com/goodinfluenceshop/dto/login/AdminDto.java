package com.goodinfluenceshop.dto.login;

import com.goodinfluenceshop.domain.Admin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
public class AdminDto {
  private String id;
  private String email;
  private String password;
  private String name;

  @Getter
  @Setter
  public static class LoginReqDto {
    private String email;
    private String password;
  }

  @Getter
  @Setter
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
  @Getter
  public static class CreateResDto {
    private String id;
  }

  @Builder
  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class SignupReqDto{
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String name;
  }
}
