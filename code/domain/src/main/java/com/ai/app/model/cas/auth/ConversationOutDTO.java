package com.ai.app.model.cas.auth;

import java.util.UUID;
import org.springaicommunity.mcp.annotation.McpToolParam;

public record ConversationOutDTO(
    @McpToolParam(description = "Unique Id related to the conversation") UUID id) {}
