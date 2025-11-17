package com.ai.app.cas.users;

import com.ai.app.cas.gen.api.UsersApi;
import com.ai.app.cas.gen.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserToolService {

  private final UsersApi usersApi;

  @McpTool(name = "who_am_i", description = "Get the current logged in user details")
  public UserResponse whoAmI() {
    return this.usersApi.getCurrentUserProfileApiUsersMeGet();
  }
}
