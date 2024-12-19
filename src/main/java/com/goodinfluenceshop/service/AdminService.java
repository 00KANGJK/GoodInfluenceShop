package com.goodinfluenceshop.service;

import com.goodinfluenceshop.domain.Admin;
import com.goodinfluenceshop.domain.AdminRoleType;
import com.goodinfluenceshop.domain.RefreshToken;
import com.goodinfluenceshop.domain.RoleType;
import com.goodinfluenceshop.dto.login.AdminDto;
import com.goodinfluenceshop.enums.LoginRoleType;
import com.goodinfluenceshop.repository.AdminRepository;
import com.goodinfluenceshop.repository.AdminRoleTypeRepository;
import com.goodinfluenceshop.repository.RefreshTokenRepository;
import com.goodinfluenceshop.util.ExternalProperties;
import com.goodinfluenceshop.util.TokenGenerator;
import com.goodinfluenceshop.repository.RoleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final AdminRepository adminRepository;
  private final AuthService authService;
  private final ExternalProperties externalProperties;
  private final RoleTypeRepository roleTypeRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final AdminRoleTypeRepository adminRoleTypeRepository;
  private final RefreshTokenRepository refreshTokenRepository;

  /**
   * 회원가입
   */
  public AdminDto.CreateResDto signup(AdminDto.SignupReqDto param) {
    // 이메일 중복 확인
    Optional<Admin> existingAdmin = Optional.ofNullable(adminRepository.findByEmail(param.getEmail()));
    if (existingAdmin.isPresent()) {
      throw new IllegalArgumentException("Email이 이미 존재합니다.");
    }

    // 비밀번호 암호화
    String encodedPassword = bCryptPasswordEncoder.encode(param.getPassword());

    // Admin 생성 및 저장
    Admin admin = Admin.builder()
      .email(param.getEmail())
      .password(encodedPassword)
      .name(param.getName())
      .build();

    admin = adminRepository.save(admin);

    // 기본 Role 추가
    RoleType roleType = roleTypeRepository.findByRoleType(LoginRoleType.ADMIN)
      .orElseGet(() -> {
        RoleType newRoleType = new RoleType();
        newRoleType.setRoleType(LoginRoleType.ADMIN);
        return roleTypeRepository.save(newRoleType);
      });

    AdminRoleType adminRoleType = AdminRoleType.of(admin, roleType);
    adminRoleTypeRepository.save(adminRoleType);

    return admin.toCreateResDto();
  }

  /**
   * Admin 로그인 메서드
   *
   * @param param Admin 로그인 요청 DTO
   * @return Admin 로그인 응답 DTO (Refresh Token 포함)
   */
  public AdminDto.CreateResDto login(AdminDto.LoginReqDto param) {
    // Admin 사용자 인증
    Admin admin = adminRepository.findByEmail(param.getEmail());
    if(admin == null) {
      throw new IllegalArgumentException("email 또는 password 유효하지 않습니다.");
    }

    // 비밀번호 검증
    if (!bCryptPasswordEncoder.matches(param.getPassword(), admin.getPassword())) {
      throw new IllegalArgumentException("email 또는 password 유효하지 않습니다.");
    }

    refreshTokenRepository.markDeletedByAdmin(admin);

    String newRefreshToken = authService.createRefreshToken(admin.getId());

    return AdminDto.CreateResDto.builder().id(newRefreshToken).build();
  }

  /**
   * Refresh Token을 기반으로 Access Token 발급
   */
  public String issueAccessToken(String refreshToken) {
    // Refresh Token 검증
    String adminId = authService.verifyRefreshToken(refreshToken);

    // Access Token 생성
    return authService.createAccessToken(adminId);
  }
}
