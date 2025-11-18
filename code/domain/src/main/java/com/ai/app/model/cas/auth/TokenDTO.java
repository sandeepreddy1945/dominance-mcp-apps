package com.ai.app.model.cas.auth;

import org.springaicommunity.mcp.annotation.McpToolParam;

public record TokenDTO(
    @McpToolParam(description = "Access Token fetched from the user response") String accessToken,
    @McpToolParam(description = "Token Type used for auth, default Bearer") String token_type,
    @McpToolParam(description = "Number of seconds until the token expires") Integer expiresIn,
    @McpToolParam(description = "User details payload fetched from the user response")
        UserFullDetailsDTO userDetails) {}
