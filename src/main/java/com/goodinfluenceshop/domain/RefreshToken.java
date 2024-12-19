package com.goodinfluenceshop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class RefreshToken extends BaseEntity {

  @Column(nullable = false, length = 2000)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY, optional = false) // Many-to-One 관계 설정
  @JoinColumn(name = "admin_id", nullable = false) // 외래 키 설정
  private Admin admin;

  public RefreshToken(String content, Admin admin) {
    this.content = content;
    this.admin = admin;
  }

  public static RefreshToken of(String content, Admin admin) {
    return new RefreshToken(content, admin);
  }
}
