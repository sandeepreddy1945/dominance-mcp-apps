package com.ai.app.application.cas.orders;

import com.ai.app.service.cas.orders.OrderToolService;
import com.ai.app.usecase.cas.orders.OrderToolUseCase;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderToolUseCaseImpl implements OrderToolUseCase {

  private final OrderToolService orderToolService;

  @Override
  @McpTool(
      name = "checkoutOrder",
      description = "Checkout an order related to the user currently in cart")
  public CallToolResult checkoutOrder() {
    return CallToolResult.builder()
        .structuredContent(this.orderToolService.checkoutOrder())
        .build();
  }

  @Override
  @McpTool(
      name = "getUserOrders",
      description = "Get all orders related to the user currently in cart")
  public CallToolResult getUserOrders() {
    // wrapping the results in a map as the CallToolResult only accepts an object as the
    // structuredContent and not lists.
    return CallToolResult.builder()
        .structuredContent(Map.of("orders", this.orderToolService.getUserOrders()))
        .build();
  }
}
