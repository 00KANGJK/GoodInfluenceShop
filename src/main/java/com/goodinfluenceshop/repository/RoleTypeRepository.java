package com.goodinfluenceshop.repository;

import com.goodinfluenceshop.domain.RoleType;
import com.goodinfluenceshop.enums.LoginRoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleTypeRepository extends JpaRepository<RoleType, String> {
  Optional<RoleType> findByRoleType(LoginRoleType roleType);
}
