package com.ai.app.model.cas.auth;

import java.util.UUID;
import org.springaicommunity.mcp.annotation.McpToolParam;

public record OrderOutDTO(@McpToolParam(description = "Unique Id related to the order") UUID id) {}
