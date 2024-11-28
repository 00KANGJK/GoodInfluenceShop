package com.goodinfluenceshop.security;

import com.goodinfluenceshop.domain.Admin;
import com.goodinfluenceshop.exception.NoMatchingDataException;
import com.goodinfluenceshop.repository.AdminRepository;
import com.goodinfluenceshop.service.AuthService;
import com.goodinfluenceshop.util.ExternalProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.function.Supplier;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final AdminRepository adminRepository;
	private final AuthService authService;
	private final ExternalProperties externalProperties;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, AdminRepository adminRepository, AuthService authService
			, ExternalProperties externalProperties
	) {
		super(authenticationManager);
		this.adminRepository = adminRepository;
		this.authService = authService;
		this.externalProperties = externalProperties;
	}

	/**
     *  권한 인가를 위한 함수.
     *  Access Token을 검증하고 유효하면 Authentication을 직접 생성해 SecurityContextHolder에 넣는다.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("request!!! : " + request);
		String jwtHeader = request.getHeader(externalProperties.getAccessKey());

    if (request.getRequestURI().startsWith("/api/all")) {
      chain.doFilter(request, response);
      return;
    }

		if(jwtHeader == null || !jwtHeader.startsWith(externalProperties.getTokenPrefix())) {
			// 토큰 없을 시 Authentication 없는 상태로 doFilter -> Spring Security가 잡아낸다.
			System.out.println("jwtHeader null");
			chain.doFilter(request, response);
			return;
		}
		String accessToken = jwtHeader.replace(externalProperties.getTokenPrefix(), "");
		System.out.println("accessToken!!! : " + accessToken);
		String adminId = authService.verifyAccessToken(accessToken);
		System.out.println("tbuserId : " + adminId);

		// 유저 조회, 없을 시 return NoMatchingDataException(404)
		Admin adminEntity = adminRepository.findEntityGraphRoleTypeById(adminId).orElseThrow(new Supplier<NoMatchingDataException>() {
			@Override
			public NoMatchingDataException get() {
				return new NoMatchingDataException("id : " + adminId);
			}
		});

		PrincipalDetails principalDetails = new PrincipalDetails(adminEntity);
		// Authentication 생성
		Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		chain.doFilter(request, response);
	}
}
