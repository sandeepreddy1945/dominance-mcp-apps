package com.ai.app.application.cas.auth;

import com.ai.app.model.cas.auth.TokenDTO;
import com.ai.app.model.cas.auth.UserFullDetailsDTO;
import com.ai.app.service.cas.auth.AuthToolService;
import com.ai.app.usecase.cas.auth.AuthToolUseCase;
import lombok.RequiredArgsConstructor;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthToolUseCaseImpl implements AuthToolUseCase {

  private final AuthToolService authToolService;

  @McpTool(
      name = "authenticate_user",
      description =
          "Authenticate a user with username and password and fetch back the user details.")
  @Override
  public TokenDTO authenticateUser(
      @McpToolParam(description = "Username of the user") String username,
      @McpToolParam(description = "Password of the user") String password) {
    return this.authToolService.authenticateUser(username, password);
  }

  @McpTool(
      name = "get_current_user_info",
      description = "Get the current logged in user details using the token")
  @Override
  public UserFullDetailsDTO getCurrentUserInfo() {
    return this.authToolService.getCurrentUserInfo();
  }

  @McpTool(name = "refresh_token", description = "Refresh the token using the refresh token")
  @Override
  public TokenDTO refreshToken() {
    return this.authToolService.refreshToken();
  }
}
