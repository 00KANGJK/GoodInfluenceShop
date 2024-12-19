package com.goodinfluenceshop.repository;

import com.goodinfluenceshop.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByContent(String Content);
    List<RefreshToken> findByAdminId(String AdminId);

  void deleteByAdminId(String id);
}
