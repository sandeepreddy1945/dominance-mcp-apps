package com.ai.app.usecase.cas.userjourney;

import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import java.util.UUID;

public interface UserJourneyToolUseCase {

  CallToolResult completePurchase(UUID productIdInCart);
}
