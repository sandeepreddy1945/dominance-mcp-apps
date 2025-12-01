package com.ai.app.usecase.cas.orders;

import io.modelcontextprotocol.spec.McpSchema.CallToolResult;

public interface OrderToolUseCase {

  CallToolResult checkoutOrder();

  CallToolResult getUserOrders();
}
