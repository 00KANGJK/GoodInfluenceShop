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

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();
		String refreshToken = authService.createRefreshToken(principalDetails.getAdmin().getId());

		// header에 담아서 전달!!
		response.addHeader(externalProperties.getRefreshKey(), externalProperties.getTokenPrefix() + refreshToken);

		System.out.println("successfulAuthentication : login success?!");
	}

}
