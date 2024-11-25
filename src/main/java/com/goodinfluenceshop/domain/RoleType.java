package com.goodinfluenceshop.domain;

import com.goodinfluenceshop.enums.LoginRoleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
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
  @OneToMany(mappedBy = "roleType",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<AdminRoleType> adminRoleType = new ArrayList<>();

  public RoleType of(String id, LoginRoleType roleType) {
    return new RoleType(id, roleType,new ArrayList<>());
  }
}
