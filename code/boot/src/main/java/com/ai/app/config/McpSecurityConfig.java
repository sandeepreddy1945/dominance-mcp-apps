package com.ai.app.config;

import org.springaicommunity.mcp.security.server.config.McpServerOAuth2Configurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

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
        //        .with(
        //            McpApiKeyConfigurer.mcpServerApiKey(),
        //            apiKey -> apiKey.apiKeyRepository(apiKeyRepository()))
        .build();
  }

  /** Provide a repository of {@link ApiKeyEntity}. */
  //  private ApiKeyEntityRepository<ApiKeyEntity> apiKeyRepository() {
  //    var apiKey =
  //        ApiKeyEntityImpl.builder()
  //            .name("sandeep test api key")
  //            .id("sandeep01")
  //            .secret("secret")
  //            .build();
  //    // use header X-API-key with value sandeep01.secret in order to authenticate
  //    return new InMemoryApiKeyEntityRepository<>(List.of(apiKey));
  //  }

  //  @Bean
  //  public JwtDecoder jwtDecoder() {
  //    // HS256 secret key (same used in FastAPI)
  //    SecretKey secretKey = new SecretKeySpec("abcdefghijklmnopqrstuvwxyz1234567890".getBytes(),
  // "HmacSHA256");
  //    return NimbusJwtDecoder.withSecretKey(secretKey)
  //        .macAlgorithm(MacAlgorithm.HS256)
  //        .build();
  //  }
}
