package com.goodinfluenceshop.controller;

import com.goodinfluenceshop.dto.login.AdminDto;
import com.goodinfluenceshop.dto.login.DefaultDto;
import com.goodinfluenceshop.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/login/admin")
@RestController
@RequiredArgsConstructor
public class LoginResController {

  private final AdminService adminService;

  @PostMapping("/access")
  public ResponseEntity<AdminDto.CreateResDto> access(HttpServletRequest request) throws Exception {
    String refreshToken = request.getHeader("refreshToken");
    System.out.println("refreshToken : " + refreshToken);
    return ResponseEntity.status(HttpStatus.OK).body(adminService.access(refreshToken));
  }

  @PostMapping
  public ResponseEntity<AdminDto.CreateResDto> login(@Valid @RequestBody AdminDto.LoginReqDto param) {
    return ResponseEntity.status(HttpStatus.CREATED).body(adminService.login(param)); // adminService 사용
  }

  @PostMapping(value = "/signup")
  public ResponseEntity<AdminDto.CreateResDto> signup(@Valid @RequestBody AdminDto.SignupReqDto param) {
    AdminDto.CreateResDto response = adminService.signup(param);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
