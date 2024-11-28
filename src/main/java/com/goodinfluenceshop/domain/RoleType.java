package com.goodinfluenceshop.domain;

import com.goodinfluenceshop.enums.LoginRoleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RoleType {
  @Id
  private String id;

  @Enumerated(EnumType.STRING)
  private LoginRoleType roleType;

  @Builder.Default
  @OneToMany(mappedBy = "roleType", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<AdminRoleType> adminRoleTypes = new ArrayList<>();  // 필드 이름을 일치시킴

  @PrePersist
  public void generateId() {
    this.id = UUID.randomUUID().toString().replace("-", ""); // 랜덤 UUID로 ID 설정
  }

  public RoleType of(String id, LoginRoleType roleType) {
    return new RoleType(id, roleType, new ArrayList<>());
  }
}
