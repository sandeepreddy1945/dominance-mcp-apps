package com.ai.app.application.cas.auth;

import com.ai.app.model.cas.auth.TokenDTO;
import com.ai.app.model.cas.auth.UserFullDetailsDTO;
import com.ai.app.service.cas.auth.AuthToolService;
import com.ai.app.usecase.cas.auth.AuthToolUseCase;
import lombok.RequiredArgsConstructor;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springaicommunity.mcp.context.McpSyncRequestContext;
import org.springframework.stereotype.Component;
import org.stringtemplate.v4.ST;

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
      McpSyncRequestContext context,
      @McpToolParam(description = "Username of the user") String username,
      @McpToolParam(description = "Password of the user") String password) {

    ST st = new ST("Trying to search for the user details with user name: <username>");
    st.add("username", username);
    context.info(st.render());

    context.progress(p -> p.progress(50).total(100).message("Authenticating user"));

    return this.authToolService.authenticateUser(username, password);
  }

  @McpTool(
      name = "get_current_user_info",
      description = "Get the current logged in user details using the token")
  @Override
  public UserFullDetailsDTO getCurrentUserInfo(McpSyncRequestContext context) {
    return this.authToolService.getCurrentUserInfo();
  }

  @McpTool(name = "refresh_token", description = "Refresh the token using the refresh token")
  @Override
  public TokenDTO refreshToken() {
    return this.authToolService.refreshToken();
  }
}
