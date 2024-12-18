package com.goodinfluenceshop.controller;

import com.goodinfluenceshop.dto.StoreDto;
import com.goodinfluenceshop.service.StoreService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/login")
@RestController
@RequiredArgsConstructor
public class UserLoginResController {
  @Autowired
  StoreService storeService;

  @PostMapping
  public ResponseEntity<StoreDto.LoginResDto> login(@RequestBody StoreDto.LoginReqDto param) {
    return ResponseEntity.status(HttpStatus.OK).body(storeService.login(param));
  }

  @PostMapping("/access")
  public ResponseEntity<StoreDto.LoginResDto> access(HttpServletRequest request) throws Exception {
    String refreshToken = request.getHeader("refreshToken");
    if (refreshToken == null || refreshToken.isEmpty()) {
      throw new IllegalArgumentException("Refresh token is required.");
    }
    return ResponseEntity.status(HttpStatus.OK).body(storeService.access(refreshToken));
  }

  @PostMapping("/signup")
  public ResponseEntity<StoreDto> signup(@RequestBody StoreDto storeDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(storeService.createStore(storeDto));
  }
}
