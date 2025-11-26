package com.ai.app.application.cas.user;

import com.ai.app.model.cas.user.UserResponseDTO;
import com.ai.app.service.cas.user.UserToolService;
import com.ai.app.usecase.cas.user.UserToolUseCase;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.ElicitResult.Action;
import io.modelcontextprotocol.spec.McpSchema.TextContent;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springaicommunity.mcp.context.McpSyncRequestContext;
import org.springaicommunity.mcp.context.StructuredElicitResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserToolUseCaseImpl implements UserToolUseCase {

  private final UserToolService userToolService;

  @Override
  @PreAuthorize("isAuthenticated()")
  @McpTool(name = "who_am_i", description = "Get the current logged in user details")
  public UserResponseDTO getUserDetails() {
    return this.userToolService.getUserDetails();
  }

  @Override
  @PreAuthorize("isAuthenticated()")
  @McpTool(
      name = "update_user_preferences",
      description = "Update the current logged in user preferences")
  public CallToolResult updateUserPreferences(
      McpSyncRequestContext context,
      @McpToolParam(description = "Full Name of the user", required = false) String fullName,
      @McpToolParam(description = "User interest preferences", required = false)
          List<String> interests,
      @McpToolParam(description = "User Language Preference Code. Default en", required = false)
          String locale) {
    if (context.elicitEnabled()) {
      if (StringUtils.isBlank(fullName)
          && CollectionUtils.isEmpty(interests)
          && StringUtils.isBlank(locale)) {
        StructuredElicitResult<UpdateUserPreferencesRequest> elicit =
            context.elicit(UpdateUserPreferencesRequest.class);
        if (elicit.action() == Action.DECLINE || elicit.action() == Action.CANCEL) {
          log.info("User declined / cancelled the elicitation request !");
          return null;
          // if its proper action just continue on the flow.
        }
        UpdateUserPreferencesRequest request = elicit.structuredContent();
        fullName = request.getFullName();
        interests =
            Optional.ofNullable(request.getInterests())
                .map(ks -> List.of(ks.split(",")))
                .orElse(null);
        locale = request.getLocale();
      } else {
        throw new UnsupportedOperationException("One of the user preferences is required!");
      }
    }

    try {
      this.userToolService.updateUserPreferences(fullName, interests, locale);
    } catch (Exception e) {
      log.error("Error updating user preferences", e);
      return CallToolResult.builder()
          .addContent(new TextContent("Error updating user preferences"))
          .isError(true)
          .build();
    }
    return CallToolResult.builder()
        .addContent(new TextContent("User preferences updated successfully"))
        .isError(false)
        .build();
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  static class UpdateUserPreferencesRequest {
    @JsonProperty String fullName;
    @JsonProperty String interests;
    @JsonProperty String locale;
  }
}
