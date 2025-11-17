package com.ai.app.config;

import com.ai.app.cas.gen.api.AuthenticationApi;
import com.ai.app.cas.gen.api.UsersApi;
import com.ai.app.cas.gen.invoker.ApiClient;
import com.ai.app.props.ClientProps;
import com.ai.app.props.RestBuilderProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(ClientProps.class)
public class CasClientConfig {

  @Bean
  public ApiClient apiClient(ClientProps clientProps) {
    RestBuilderProps restBuilderProps = clientProps.get("cas-client");

    RestClient client = RestClient.builder()
        .baseUrl(restBuilderProps.getBaseUrl())
        .defaultHeader("Authorization", "Bearer " + restBuilderProps.getToken())
        .defaultHeader("Content-Type", "application/json")
        .defaultHeader("Accept", "application/json")
        .build();
    return new ApiClient(client);
  }

  @Bean
  public AuthenticationApi authenticationApi(ApiClient apiClient) {
    return new AuthenticationApi(apiClient);
  }

  @Bean
  public UsersApi usersApi(ApiClient apiClient) {
    return new UsersApi(apiClient);
  }
}
