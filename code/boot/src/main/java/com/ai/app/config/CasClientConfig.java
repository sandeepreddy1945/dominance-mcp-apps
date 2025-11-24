package com.ai.app.config;

import com.ai.app.application.cas.security.AuthHeaderInterceptor;
import com.ai.app.cas.gen.api.AuthenticationApi;
import com.ai.app.cas.gen.api.UsersApi;
import com.ai.app.cas.gen.invoker.ApiClient;
import com.ai.app.common.props.ClientProps;
import com.ai.app.common.props.RestBuilderProps;
import java.net.http.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class CasClientConfig {

  @Bean
  public ApiClient apiClient(ClientProps clientProps, AuthHeaderInterceptor authHeaderInterceptor) {
    RestBuilderProps restBuilderProps = clientProps.get("cas-client");

    // use Http 1.1 in order to escape from the Uvicron issues on cas app
    HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
    JdkClientHttpRequestFactory jdkClientHttpRequestFactory =
        new JdkClientHttpRequestFactory(httpClient);

    RestClient client =
        RestClient.builder()
            .baseUrl(restBuilderProps.getBaseUrl())
            .defaultHeader("Authorization", "Bearer " + restBuilderProps.getToken())
            .defaultHeader("Content-Type", "application/json")
            .defaultHeader("Accept", "application/json")
            .requestFactory(jdkClientHttpRequestFactory)
            .requestInterceptor(authHeaderInterceptor)
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
