package com.ai.app.model.cas.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springaicommunity.mcp.annotation.McpToolParam;

public record UserResponseDTO(
    @McpToolParam(description = "Username of the user") String username,
    @McpToolParam(description = "Email of the user") String email,
    @McpToolParam(description = "Full Name of the user", required = false) String fullName,
    @McpToolParam(
            description = "Default Language setting for the user, Default is 'en'",
            required = false)
        String locale,
    @McpToolParam(description = "Interests of the user", required = false) List<String> interests,
    @McpToolParam(description = "User Preferences", required = false)
        Map<String, Object> preferences,
    @McpToolParam(description = "Unique id related to the user") UUID id,
    @McpToolParam(description = "Is the user active flag") Boolean isActive,
    @McpToolParam(description = "Is the user super / admin user flag") Boolean isSuperuser,
    @McpToolParam(description = "Url of the image related to the user avatar", required = false)
        String avatarUrl,
    @McpToolParam(description = "Created Time") LocalDateTime createdAt,
    @McpToolParam(description = "Users last login / active time") LocalDateTime lastActive) {}
