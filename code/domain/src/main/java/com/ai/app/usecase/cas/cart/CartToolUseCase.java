package com.ai.app.usecase.cas.cart;

import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import java.util.UUID;

public interface CartToolUseCase {

  CallToolResult addItemToCart(UUID productId, Integer quantity);

  CallToolResult getCartSummary();

  CallToolResult getCurrentCartItemDetails();
}
