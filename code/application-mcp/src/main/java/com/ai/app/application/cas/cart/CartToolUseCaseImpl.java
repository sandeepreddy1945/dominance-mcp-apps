package com.ai.app.application.cas.cart;

import com.ai.app.service.cas.cart.CartToolService;
import com.ai.app.usecase.cas.cart.CartToolUseCase;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CartToolUseCaseImpl implements CartToolUseCase {

  private final CartToolService cartToolService;

  @Override
  @McpTool(name = "addItemToCart", description = "Add item to cart")
  public CallToolResult addItemToCart(
      @McpToolParam(description = "Unique product id that will be fetched from the chat history")
          UUID productId,
      @McpToolParam(description = "Quantity of the product to be added to the cart")
          Integer quantity) {
    return CallToolResult.builder()
        .structuredContent(this.cartToolService.addItemToCart(productId, quantity))
        .build();
  }

  @Override
  @McpTool(name = "getCartSummary", description = "Get cart summary")
  public CallToolResult getCartSummary() {
    return CallToolResult.builder()
        .structuredContent(this.cartToolService.getCartSummary())
        .build();
  }

  @Override
  @McpTool(name = "getCurrentCartItemDetails", description = "Get current cart item details")
  public CallToolResult getCurrentCartItemDetails() {
    return CallToolResult.builder()
        .structuredContent(this.cartToolService.getCurrentCartItemDetails())
        .build();
  }
}
