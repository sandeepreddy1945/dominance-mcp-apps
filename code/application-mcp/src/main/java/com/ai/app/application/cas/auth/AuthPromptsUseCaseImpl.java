package com.ai.app.application.cas.auth;

import com.ai.app.usecase.cas.auth.AuthPromptsUseCase;
import io.modelcontextprotocol.spec.McpSchema.GetPromptResult;
import io.modelcontextprotocol.spec.McpSchema.PromptMessage;
import io.modelcontextprotocol.spec.McpSchema.Role;
import io.modelcontextprotocol.spec.McpSchema.TextContent;
import java.util.List;
import org.springaicommunity.mcp.annotation.McpPrompt;
import org.springframework.stereotype.Component;

@Component
public class AuthPromptsUseCaseImpl implements AuthPromptsUseCase {

  @Override
  @McpPrompt(name = "user_auth_prompt")
  public GetPromptResult getUserAuthPrompt() {
    String message =
        """
            You are an assistant that enforces authentication and registration rules:
            If the user is not authenticated, do not perform any task. Tell them they must authenticate first.
            When the user requests their personal details, verify authentication first; if not authenticated, require it.
            If the user is new or unregistered, direct them to the registration process before proceeding.
            Never complete protected actions without authentication.
            """;

    return new GetPromptResult(
        "Authenticate User", List.of(new PromptMessage(Role.ASSISTANT, new TextContent(message))));
  }

  //  @McpPrompt(name = "authenticate_user_before_operations")
  //  public SystemMessage authenticateUserBeforeOperations() {
  //    String message =
  //        """
  //        You are an assistant that enforces authentication and registration rules:
  //        If the user is not authenticated, do not perform any task. Tell them they must
  // authenticate first.
  //        When the user requests their personal details, verify authentication first; if not
  // authenticated, require it.
  //        If the user is new or unregistered, direct them to the registration process before
  // proceeding.
  //        Never complete protected actions without authentication.
  //        """;
  //    return new SystemMessage(message);
  //  }
}
