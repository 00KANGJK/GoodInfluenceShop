package com.goodinfluenceshop.controller;

import com.goodinfluenceshop.dto.login.AdminDto;
import com.goodinfluenceshop.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/login/admin")
@RestController
@RequiredArgsConstructor
public class AdminLoginResController {

  private final AdminService adminService;

  /**
   * 회원가입
   */
  @PostMapping("/signup")
  public ResponseEntity<AdminDto.CreateResDto> signup(@Valid @RequestBody AdminDto.SignupReqDto param) {
    AdminDto.CreateResDto response = adminService.signup(param);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  /**
   * 로그인
   */
  @PostMapping
  public ResponseEntity<AdminDto.CreateResDto> login(@Valid @RequestBody AdminDto.LoginReqDto param) {
    AdminDto.CreateResDto response = adminService.login(param);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  /**
   * Refresh Token을 기반으로 Access Token 발급
   */
  @PostMapping("/access")
  public ResponseEntity<String> issueAccessToken(HttpServletRequest request) {
    String refreshToken = request.getHeader("refreshToken");
    if (refreshToken == null || refreshToken.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing refreshToken header");
    }

    String accessToken = adminService.issueAccessToken(refreshToken);
    return ResponseEntity.status(HttpStatus.OK).body(accessToken);
  }
}
