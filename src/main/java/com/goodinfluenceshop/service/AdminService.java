package com.goodinfluenceshop.service;

import com.goodinfluenceshop.domain.Admin;
import com.goodinfluenceshop.domain.AdminRoleType;
import com.goodinfluenceshop.domain.RoleType;
import com.goodinfluenceshop.dto.login.AdminDto;
import com.goodinfluenceshop.dto.login.DefaultDto;
import com.goodinfluenceshop.enums.LoginRoleType;
import com.goodinfluenceshop.repository.AdminRepository;
import com.goodinfluenceshop.repository.AdminRoleTypeRepository;
import com.goodinfluenceshop.util.ExternalProperties;
import com.goodinfluenceshop.util.TokenGenerator;
import com.goodinfluenceshop.repository.RoleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final AdminRepository adminRepository;
  private final AuthService authService;
  private final ExternalProperties externalProperties;
  private final RoleTypeRepository roleTypeRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final AdminRoleTypeRepository tbuserRoleTypeRepository;

  public AdminDto.CreateResDto access(String param) throws Exception {

    param = param.replace(externalProperties.getTokenPrefix(), "");
    System.out.println("refreshToken ?!!! : " + param);
    String accessToken = authService.issueAccessToken(param);

    return AdminDto.CreateResDto.builder().id(accessToken).build();
  }

  public AdminDto.CreateResDto logout(DefaultDto.DetailReqDto param){
    return AdminDto.CreateResDto.builder().id("logout").build();
  }

  public AdminDto.CreateResDto login(AdminDto.LoginReqDto param){
    Admin admin = adminRepository.findByEmailAndPassword(param.getEmail(), param.getPassword());
    if(admin == null){
      return AdminDto.CreateResDto.builder().id("not matched").build();
    }
    TokenGenerator tokenGenerator = new TokenGenerator();
    String refreshToken = tokenGenerator.issueRefreshToken(admin.getId());

    return AdminDto.CreateResDto.builder().id(refreshToken).build();
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
