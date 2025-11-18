package com.ai.app.cas.auth;

import com.ai.app.cas.gen.api.AuthenticationApi;
import com.ai.app.cas.gen.model.Token;
import com.ai.app.cas.gen.model.UserFullResponse;
import com.ai.app.cas.gen.model.UserLogin;
import lombok.RequiredArgsConstructor;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthToolService {

  private final AuthenticationApi authenticationApi;

  @McpTool(
      name = "authenticate_user",
      description =
          "Authenticate a user with username and password and fetch back the user details.")
  public Token authenticateUser(
      @McpToolParam(description = "Username of the user") String username,
      @McpToolParam(description = "Password of the user") String password) {
    return this.authenticationApi.loginApiAuthLoginPost(
        new UserLogin().username(username).password(password));
  }

  @McpTool(
      name = "get_current_user_info",
      description = "Get the current logged in user details using the token")
  public UserFullResponse getCurrentUserInfo() {
    return this.authenticationApi.getCurrentUserInfoApiAuthMeGet();
  }

  @McpTool(name = "refresh_token", description = "Refresh the token using the refresh token")
  public Token refreshToken() {
    return this.authenticationApi.refreshTokenApiAuthRefreshPost();
  }
}
