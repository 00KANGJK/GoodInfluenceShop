package com.goodinfluenceshop.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.algorithms.Algorithm;
import com.goodinfluenceshop.domain.Admin;
import com.goodinfluenceshop.domain.RefreshToken;
import com.goodinfluenceshop.repository.AdminRepository;
import com.goodinfluenceshop.repository.RefreshTokenRepository;
import com.goodinfluenceshop.util.ExternalProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final ExternalProperties externalProperties;
  private final RefreshTokenRepository refreshTokenRepository;
  private final AdminRepository adminRepository;

  public Algorithm getTokenAlgorithm() {
    return Algorithm.HMAC512(externalProperties.getTokenSecretKey());
  }

  /**
   * Access Token 생성
   */
  public String createAccessToken(String adminId) {
    return JWT.create()
      .withSubject("accessToken")
      .withClaim("id", adminId)
      .withExpiresAt(new Date(System.currentTimeMillis() + externalProperties.getAccessTokenExpirationTime()))
      .sign(getTokenAlgorithm());
  }

  /**
   * Access Token 검증
   */
  public String verifyAccessToken(String accessToken) throws JWTVerificationException {
    return JWT.require(getTokenAlgorithm())
      .build()
      .verify(accessToken)
      .getClaim("id")
      .asString();
  }

  /**
   * Refresh Token 생성
   */
  @Transactional
  public String createRefreshToken(String adminId) {
    // Admin 조회
    Admin admin = adminRepository.findById(adminId)
      .orElseThrow(() -> new IllegalArgumentException("Admin not found"));

    // 기존 Refresh Token 삭제
    revokeRefreshToken(admin);
    System.out.println("delete refresh token");

    // 새 Refresh Token 생성
    String refreshTokenContent = JWT.create()
      .withSubject("refreshToken")
      .withClaim("id", adminId)
      .withExpiresAt(new Date(System.currentTimeMillis() + externalProperties.getRefreshTokenExpirationTime()))
      .sign(getTokenAlgorithm());

    // 새 Refresh Token 저장
    RefreshToken refreshToken = new RefreshToken(refreshTokenContent, admin);
    refreshTokenRepository.save(refreshToken);

    return refreshTokenContent;
  }

  /**
   * Refresh Token 폐기
   */
  @Transactional
  public void revokeRefreshToken(Admin admin) {
    refreshTokenRepository.markDeletedByAdmin(admin);
  }

  /**
   * Refresh Token 검증
   */
  public String verifyRefreshToken(String refreshToken) throws JWTVerificationException {
    refreshTokenRepository.findByContentAndDeleted(refreshToken,"N")
      .orElseThrow(() -> new IllegalArgumentException("Refresh Token이 유효하지 않습니다."));

    return JWT.require(getTokenAlgorithm())
      .build()
      .verify(refreshToken)
      .getClaim("id")
      .asString();
  }

  /**
   * Access Token 발급
   * Refresh Token이 유효하면 Access Token 발급
   */
  public String issueAccessToken(String refreshToken) {
    // Refresh Token 검증
    String adminId = verifyRefreshToken(refreshToken);

    // Access Token 생성
    return createAccessToken(adminId);
  }
}
