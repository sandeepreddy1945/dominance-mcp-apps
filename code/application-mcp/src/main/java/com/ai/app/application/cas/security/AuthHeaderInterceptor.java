package com.ai.app.application.cas.security;

import java.io.IOException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * Interceptor to add Authorization header to the request. This will help the current app to access
 * the protected resources of the cas app using keycloak auth.
 */
@Component
public class AuthHeaderInterceptor implements ClientHttpRequestInterceptor {

  private final AuthTokenProvider tokenProvider;

  public AuthHeaderInterceptor(AuthTokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  @Override
  public ClientHttpResponse intercept(
      HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

    String token = tokenProvider.getToken();
    request.getHeaders().set("Authorization", "Bearer " + token);

    return execution.execute(request, body);
  }
}
