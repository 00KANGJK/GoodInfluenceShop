package com.goodinfluenceshop.repository;

import com.goodinfluenceshop.domain.RoleType;
import com.goodinfluenceshop.enums.LoginRoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleTypeRepository extends JpaRepository<RoleType, String> {
  RoleType findByRoleType(LoginRoleType roleType);
}
