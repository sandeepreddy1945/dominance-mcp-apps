package com.ai.app.usecase.cas.user;

import com.ai.app.model.cas.user.UserResponseDTO;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import java.util.List;
import org.springaicommunity.mcp.context.McpSyncRequestContext;

public interface UserToolUseCase {

  UserResponseDTO getUserDetails();

  CallToolResult updateUserPreferences(
      McpSyncRequestContext context, String fullName, List<String> interests, String locale);
}
