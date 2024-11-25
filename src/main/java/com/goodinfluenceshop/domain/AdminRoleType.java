package com.goodinfluenceshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class AdminRoleType {
  @Id
  private String id; // ID를 String으로 정의

  private String deleted; // 삭제여부
  private LocalDateTime createdDate; // 생성일시
  private LocalDateTime modifyDate; // 수정일시

  @ManyToOne
  @JoinColumn(name = "admin_id")
  private Admin admin;

  @ManyToOne
  @JoinColumn(name = "role_type_id")
  private RoleType roleType;

  protected AdminRoleType() {}
  private AdminRoleType(Admin admin, RoleType roleType) {
    this.admin = admin;
    this.roleType = roleType;
  }

  public static AdminRoleType of(Admin admin, RoleType roleType) {
    return new AdminRoleType(admin, roleType);
  }
}
