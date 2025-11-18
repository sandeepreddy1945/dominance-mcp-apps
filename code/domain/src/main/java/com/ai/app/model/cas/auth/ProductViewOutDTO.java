package com.ai.app.model.cas.auth;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springaicommunity.mcp.annotation.McpToolParam;

public record ProductViewOutDTO(
    @McpToolParam(description = "Unique Id related to the product view") UUID id,
    @McpToolParam(description = "Unique Id of the Product") UUID productId,
    @McpToolParam(description = "Last user view date of that product") LocalDateTime viewedAt) {}
