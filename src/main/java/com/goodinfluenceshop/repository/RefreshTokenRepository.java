package com.goodinfluenceshop.repository;

import com.goodinfluenceshop.domain.Admin;
import com.goodinfluenceshop.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByContentAndDeleted(String content, String deleted);

  @Modifying
  @Query("UPDATE RefreshToken rt SET rt.deleted = 'Y' WHERE rt.admin = :admin")
  void markDeletedByAdmin(@Param("admin") Admin admin);
}

