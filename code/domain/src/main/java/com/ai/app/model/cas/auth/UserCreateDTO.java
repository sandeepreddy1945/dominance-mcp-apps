package com.ai.app.model.cas.auth;

import org.springaicommunity.mcp.annotation.McpToolParam;

/**
 * In this DTO only the minimal necessary details are taken and any further set like interests,
 * preferences etc. are not to be considered by using an update call.
 */
public record UserCreateDTO(
    @McpToolParam(description = "Chosen username of the user") String username,
    @McpToolParam(description = "Chosen password of the user") String password,
    @McpToolParam(description = "Chosen email of the user") String email,
    @McpToolParam(description = "Full Name of the user", required = false) String fullName) {}
