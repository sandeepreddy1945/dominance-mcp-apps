package com.ai.app.application.cas.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthTokenProvider {

  public String getToken() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof Jwt jwt) {
      log.debug("Jwt Token: {}", jwt);
      return jwt.getTokenValue();
    }

    throw new IllegalArgumentException("Invalid JWT Principal found!");
  }
}
