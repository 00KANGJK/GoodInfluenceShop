package com.goodinfluenceshop.repository;

import com.goodinfluenceshop.domain.Admin;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {
  Admin findByEmail(String email);
  Admin findByEmailAndPassword(String email, String password);

  @EntityGraph(attributePaths = {"adminRoleTypes", "adminRoleTypes.roleType"})
  Optional<Admin> findEntityGraphRoleTypeById(String id);
}
