package com.goodinfluenceshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsFilterConfiguration {

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.setAllowedOrigins(Arrays.asList(
            "http://localhost:3000",
            "https://ginfluencer-client-front.vercel.app",
            "https://ginfluencer-admin-front.vercel.app"
    ));
    config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", "RefreshToken"));
    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
    config.setExposedHeaders(Arrays.asList("Authorization", "RefreshToken")); // 추가된 부분

    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}

