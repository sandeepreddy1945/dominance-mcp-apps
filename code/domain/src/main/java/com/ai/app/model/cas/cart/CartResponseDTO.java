package com.ai.app.model.cas.cart;

import java.math.BigDecimal;
import java.util.List;
import org.springaicommunity.mcp.annotation.McpToolParam;

public record CartResponseDTO(
    @McpToolParam(description = "List of items in the cart") List<CartItemDTO> items,
    @McpToolParam(description = "Total number of items in the cart") Integer totalItems,
    @McpToolParam(description = "Total amount of the cart") BigDecimal totalAmount,
    @McpToolParam(description = "Currency of the cart. Default USD") String currency) {}
