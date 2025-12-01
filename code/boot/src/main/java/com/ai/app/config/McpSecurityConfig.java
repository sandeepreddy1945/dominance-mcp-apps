package com.ai.app.config;

import java.util.Arrays;
import java.util.Collections;
import org.springaicommunity.mcp.security.server.config.McpServerOAuth2Configurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class McpSecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(
      HttpSecurity http,
      @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}") String issuerUri)
      throws Exception {

    return http
        // Open every request on the server
        .authorizeHttpRequests(
            auth -> {
              auth.requestMatchers("/mcp").permitAll();
              auth.anyRequest().authenticated();
            })
        .with(
            McpServerOAuth2Configurer.mcpServerOAuth2(),
            auth -> auth.authorizationServer(issuerUri))
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(AbstractHttpConfigurer::disable)
        .build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Collections.singletonList("*")); // Allow all origins
    configuration.setAllowedMethods(
        Arrays.asList(
            "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")); // Allow all common methods
    configuration.setAllowedHeaders(Collections.singletonList("*")); // Allow all headers

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration); // Apply this configuration to all paths
    return source;
  }
}
