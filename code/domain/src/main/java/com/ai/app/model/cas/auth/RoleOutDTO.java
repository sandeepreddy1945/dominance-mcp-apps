package com.ai.app.model.cas.auth;

import java.util.List;
import org.springaicommunity.mcp.annotation.McpToolParam;

public record RoleOutDTO(
    @McpToolParam(description = "Unique Id related to the Role") Integer id,
    @McpToolParam(description = "Role Name") String name,
    @McpToolParam(description = "Description related to the role", required = false)
        String description,
    @McpToolParam(description = "Permissions related to the role")
        List<PermissionsDTO> permissions) {}
