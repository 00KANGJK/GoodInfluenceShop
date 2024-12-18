package com.goodinfluenceshop.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.goodinfluenceshop.repository.RefreshTokenRepository;
import com.goodinfluenceshop.util.ExternalProperties;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final ExternalProperties externalProperties;
  private final RefreshTokenRepository refreshTokenRepository;

  public Algorithm getTokenAlgorithm() {
    return Algorithm.HMAC512(externalProperties.getTokenSecretKey());
  }

  public String createAccessToken(String adminId) { //accessToken 생성
    return JWT.create()
      .withSubject("accessToken")
      .withClaim("id", adminId)
      .withExpiresAt(new Date(System.currentTimeMillis()+externalProperties.getAccessTokenExpirationTime()))
      .sign(getTokenAlgorithm());
  }

  public String verifyAccessToken(String accessToken) throws JWTVerificationException{ //accessToken 검증
    return JWT.require(getTokenAlgorithm())
      .build()
      .verify(accessToken)
      .getClaim("id").asString();
  }

  public String createRefreshToken(String adminId) { //refreshToken 생성
    return JWT.create()
      .withSubject("refreshToken")
      .withClaim("id", adminId)
      .withExpiresAt(new Date(System.currentTimeMillis()+externalProperties.getRefreshTokenExpirationTime()))
      .sign(getTokenAlgorithm());
  }

  public void revokeRefreshToken(String adminId) { //refreshToken 폐기
    refreshTokenRepository.deleteAll(refreshTokenRepository.findByAdminId(adminId));
  }

  public String verifyRefreshToken(String refreshToken) throws JWTVerificationException{ //refreshToken 검증
    return JWT.require(getTokenAlgorithm())
      .build()
      .verify(refreshToken)
      .getClaim("id").asString();
  }

  public String issueAccessToken(String refreshToken) throws JWTVerificationException { //accessToken 발급
    String adminId = verifyRefreshToken(refreshToken);
    return createAccessToken(adminId);
  }

}
