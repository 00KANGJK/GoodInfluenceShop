package com.goodinfluenceshop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodinfluenceshop.enums.LoginRoleType;
import com.goodinfluenceshop.repository.AdminRepository;
import com.goodinfluenceshop.security.FilterExceptionHandlerFilter;
import com.goodinfluenceshop.security.JwtAuthenticationFilter;
import com.goodinfluenceshop.security.JwtAuthorizationFilter;
import com.goodinfluenceshop.service.AuthService;
import com.goodinfluenceshop.util.ExternalProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

  private final AdminRepository adminRepository;
  private final CorsFilterConfiguration corsFilterConfiguration;
  private final ObjectMapper objectMapper;
  private final AuthService authService;
  private final ExternalProperties externalProperties;

  public SecurityConfig(AdminRepository adminRepository, CorsFilterConfiguration corsFilterConfiguration, ObjectMapper objectMapper, AuthService authService, ExternalProperties externalProperties) {
    this.adminRepository = adminRepository;
    this.corsFilterConfiguration = corsFilterConfiguration;
    this.objectMapper = objectMapper;
    this.authService = authService;
    this.externalProperties = externalProperties;
  }

  @Bean
  BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
      .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .formLogin(AbstractHttpConfigurer::disable)
      .httpBasic(AbstractHttpConfigurer::disable)
      .addFilter(corsFilterConfiguration.corsFilter())
      .authorizeHttpRequests(auth -> {
        auth.requestMatchers("/api/admin/**").hasAuthority("ADMIN") // 관리자만 접근 가능
          .anyRequest().permitAll(); // 그 외 요청은 인증 필요
      })
      .apply(new CustomDsl());

    return http.build();
  }

  public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {

    @Override
    public void configure(HttpSecurity http) throws Exception {
      AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
      JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, objectMapper, authService, externalProperties);
      System.out.println("jwtAuthenticationFilter : " + jwtAuthenticationFilter);
      jwtAuthenticationFilter.setFilterProcessesUrl("/api/all/login");

      http.addFilter(corsFilterConfiguration.corsFilter())
        .addFilter(jwtAuthenticationFilter)
        .addFilter(new JwtAuthorizationFilter(authenticationManager, adminRepository, authService, externalProperties))
        .addFilterBefore(new FilterExceptionHandlerFilter(), BasicAuthenticationFilter.class);
    }
  }
}