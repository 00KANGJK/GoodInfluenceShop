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

@Service
@RequiredArgsConstructor
public class AdminService {

  private final AdminRepository adminRepository;
  private final AuthService authService;
  private final ExternalProperties externalProperties;
  private final RoleTypeRepository roleTypeRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final AdminRoleTypeRepository tbuserRoleTypeRepository;
  private final RefreshTokenRepository refreshTokenRepository;

  public AdminDto.CreateResDto access(String param) throws Exception {

    param = param.replace(externalProperties.getTokenPrefix(), "");
    System.out.println("refreshToken ?!!! : " + param);
    String accessToken = authService.issueAccessToken(param);

    return AdminDto.CreateResDto.builder().id(accessToken).build();
  }

  public AdminDto.CreateResDto login(AdminDto.LoginReqDto param){
    Admin admin = adminRepository.findByEmailAndPassword(param.getEmail(), param.getPassword());
    if(admin == null){
      return AdminDto.CreateResDto.builder().id("not matched").build();
    }
    // 기존 Refresh Token 제거
    refreshTokenRepository.deleteByAdminId(admin.getId());

    // 새 Refresh Token 생성
    TokenGenerator tokenGenerator = new TokenGenerator();
    String newRefreshToken = tokenGenerator.issueRefreshToken(admin.getId());

    // 새 Refresh Token 저장
    RefreshToken refreshToken = new RefreshToken(admin.getId(), newRefreshToken);
    refreshTokenRepository.save(refreshToken);

    // 새 토큰 반환
    return AdminDto.CreateResDto.builder().id(newRefreshToken).build();
  }

  public AdminDto.CreateResDto signup(AdminDto.SignupReqDto param){
    AdminDto.CreateReqDto newParam = AdminDto.CreateReqDto.builder().email(param.getEmail()).password(param.getPassword()).build();
    return create(newParam);
  }

  public AdminDto.CreateResDto create(AdminDto.CreateReqDto param) {
    //비번 암호화를 위한 코드\
    param.setPassword(bCryptPasswordEncoder.encode(param.getPassword()));

    //사용자 등록 완료!/
    Admin admin = adminRepository.save(param.toEntity());

    RoleType roleType = roleTypeRepository.findByRoleType(LoginRoleType.ADMIN);
    if(roleType == null){
      roleType = new RoleType();
      roleType.setRoleType(LoginRoleType.ADMIN);
      roleTypeRepository.save(roleType);
    }
    AdminRoleType adminRoleType = AdminRoleType.of(admin, roleType);
    System.out.println("adminRoleType : " + adminRoleType.getRoleType().getRoleType());
    tbuserRoleTypeRepository.save(adminRoleType);
    return admin.toCreateResDto();
  }
}
