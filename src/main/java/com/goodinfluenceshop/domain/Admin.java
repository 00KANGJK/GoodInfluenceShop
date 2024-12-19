package com.goodinfluenceshop.domain;

import com.goodinfluenceshop.dto.login.AdminDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Admin {

  @Id
  private String id; // ID를 String으로 정의

  private String email;
  private String password;
  private String name;

  private String deleted; // 삭제 여부
  private LocalDateTime createdDate; // 생성일시
  private LocalDateTime modifyDate; // 수정일시

  @OneToMany(mappedBy = "admin", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<AdminRoleType> adminRoleTypes = new ArrayList<>();

  @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<RefreshToken> refreshTokens = new ArrayList<>(); // RefreshToken과의 관계

  @PrePersist
  public void onPrePersist() {
    this.deleted = "N"; // 기본 삭제 여부 설정
    this.createdDate = LocalDateTime.now(); // 생성일시 설정
    this.modifyDate = LocalDateTime.now(); // 수정일시 설정
    this.id = UUID.randomUUID().toString().replace("-", ""); // UUID를 문자열로 변환하여 ID로 사용
  }

  public List<AdminRoleType> getAdminRoleTypes() {
    return adminRoleTypes.isEmpty() ? new ArrayList<>() : adminRoleTypes;
  }

  public List<AdminRoleType> getRoleList() {
    return adminRoleTypes.isEmpty() ? new ArrayList<>() : adminRoleTypes;
  }

  public AdminDto.CreateResDto toCreateResDto() {
    return AdminDto.CreateResDto
      .builder()
      .id(this.getId())
      .build();
  }

  @PreUpdate
  public void onPreUpdate() {
    this.modifyDate = LocalDateTime.now(); // 수정 시 수정일시 업데이트
  }
}
