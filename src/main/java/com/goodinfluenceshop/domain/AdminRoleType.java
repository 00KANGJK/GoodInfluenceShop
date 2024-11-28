package com.goodinfluenceshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor // 기본 생성자 추가
public class AdminRoleType {

  @Id
  private String id; // ID를 String으로 정의

  private String deleted; // 삭제 여부
  private LocalDateTime createdDate; // 생성일시
  private LocalDateTime modifyDate; // 수정일시

  @ManyToOne
  @JoinColumn(name = "admin_id")
  private Admin admin; // Admin과의 다대일 관계

  @ManyToOne
  @JoinColumn(name = "role_type_id")
  private RoleType roleType; // RoleType과의 다대일 관계

  /**
   * 생성자 (admin과 roleType 설정)
   */
  private AdminRoleType(Admin admin, RoleType roleType) {
    this.id = UUID.randomUUID().toString().replace("-", ""); // 랜덤한 UUID로 ID 설정
    this.admin = admin;
    this.roleType = roleType;
    this.deleted = "N"; // 기본 삭제 여부 설정
    this.createdDate = LocalDateTime.now(); // 생성일시 설정
    this.modifyDate = LocalDateTime.now(); // 수정일시 설정
  }

  /**
   * 정적 팩토리 메서드
   * Admin과 RoleType을 받아 AdminRoleType 객체를 생성한다.
   */
  public static AdminRoleType of(Admin admin, RoleType roleType) {
    return new AdminRoleType(admin, roleType);
  }

  /**
   * 수정 시 자동으로 수정일시 업데이트
   */
  public void updateModifyDate() {
    this.modifyDate = LocalDateTime.now();
  }
}
