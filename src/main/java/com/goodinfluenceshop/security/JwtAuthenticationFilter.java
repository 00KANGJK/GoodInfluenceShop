package com.goodinfluenceshop.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.goodinfluenceshop.dto.login.AdminDto;
import com.goodinfluenceshop.service.AuthService;
import com.goodinfluenceshop.util.ExternalProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Transactional
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final ObjectMapper objectMapper;
	private final AuthService authService;
	private final ExternalProperties externalProperties;

	/**
     *  로그인하려는 사용자의 자격을 확인해 토큰을 발급하는 함수.
	 *  "/api/login" 으로 들어오는 요청에 실행된다.
	 *  생성된 Authentication이 SecurityContextHolder에 등록되어 권한처리가 가능하게 한다.
	 *
	 *  @throws AuthenticationException
	 */
	@Transactional
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		Authentication authentication = null;
		AdminDto.LoginReqDto adminLoginDto = null;
		try {
      adminLoginDto = objectMapper.readValue(request.getInputStream(), AdminDto.LoginReqDto.class);
		} catch (IOException e) {
			System.out.println("1. login attemptAuthentication : Not Enough Parameters?!");
		}

		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(adminLoginDto.getEmail(), adminLoginDto.getPassword());
			authentication = authenticationManager.authenticate(authenticationToken);
		} catch (AuthenticationException e) {
			System.out.println("2. login attemptAuthentication : username, password Not Mathced?!");
		}

		return authentication;
	}

	/**
     *  로그인 완료시 호출되는 함수.
     *  Refresh Token을 발급해 HttpServletRespons에 담는다.
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();
		String refreshToken = authService.createRefreshToken(principalDetails.getAdmin().getId());

		// header에 담아서 전달!!
		response.addHeader(externalProperties.getRefreshKey(), externalProperties.getTokenPrefix() + refreshToken);

		System.out.println("successfulAuthentication : login success?!");
	}

}
