package com.ai.app.model.cas.auth;

import org.springaicommunity.mcp.annotation.McpToolParam;

public record PermissionsDTO(
    @McpToolParam(description = "Unique is related to the permission") Integer id,
    @McpToolParam(description = "Role Name") String name,
    @McpToolParam(description = "Description related to the role", required = false)
        String description) {}
