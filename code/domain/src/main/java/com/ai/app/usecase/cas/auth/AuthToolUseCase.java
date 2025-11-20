package com.ai.app.usecase.cas.auth;

import com.ai.app.model.cas.auth.TokenDTO;
import com.ai.app.model.cas.auth.UserCreateDTO;
import com.ai.app.model.cas.auth.UserFullDetailsDTO;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import org.springaicommunity.mcp.context.McpSyncRequestContext;

public interface AuthToolUseCase {

  TokenDTO authenticateUser(McpSyncRequestContext context, String username, String password);

  UserFullDetailsDTO getCurrentUserInfo(McpSyncRequestContext context);

  TokenDTO refreshToken();

  CallToolResult registerUser(McpSyncRequestContext context, UserCreateDTO userCreateDTO);
}
