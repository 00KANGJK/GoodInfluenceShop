package com.goodinfluenceshop.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class RefreshToken extends BaseEntity{
  private String content;
  private String adminId;

  public RefreshToken(String content, String adminId) {
    this.content = content;
    this.adminId = adminId;
  }

  public static RefreshToken of(String content, String adminId) {
        return new RefreshToken(content, adminId);
  }
}
