package com.ai.app.config;

import java.util.List;
import org.springaicommunity.mcp.security.server.apikey.ApiKeyEntity;
import org.springaicommunity.mcp.security.server.apikey.ApiKeyEntityRepository;
import org.springaicommunity.mcp.security.server.apikey.memory.ApiKeyEntityImpl;
import org.springaicommunity.mcp.security.server.apikey.memory.InMemoryApiKeyEntityRepository;
import org.springaicommunity.mcp.security.server.config.McpApiKeyConfigurer;
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
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    return http
        // Open every request on the server
        .authorizeHttpRequests(
            auth -> {
              auth.requestMatchers("/mcp").permitAll();
              auth.anyRequest().authenticated();
            })
        .with(
            McpApiKeyConfigurer.mcpServerApiKey(),
            apiKey -> apiKey.apiKeyRepository(apiKeyRepository()))
        .build();
  }

  /** Provide a repository of {@link ApiKeyEntity}. */
  private ApiKeyEntityRepository<ApiKeyEntity> apiKeyRepository() {
    var apiKey =
        ApiKeyEntityImpl.builder()
            .name("sandeep test api key")
            .id("sandeep01")
            .secret("secret")
            .build();
    // use header X-API-key with value sandeep01.secret in order to authenticate
    return new InMemoryApiKeyEntityRepository<>(List.of(apiKey));
  }
}
