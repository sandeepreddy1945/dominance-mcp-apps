package com.ai.app.model.cas.cart;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springaicommunity.mcp.annotation.McpToolParam;

public record CartItemDTO(
    @McpToolParam(description = "Unique Id related to the cart order") UUID id,
    @McpToolParam(description = "Unique Id related to the product Id added to the cart")
        UUID productId,
    @McpToolParam(description = "Name of the product added to the cart") String productName,
    @McpToolParam(description = "Code of the product added to the cart") String productCode,
    @McpToolParam(description = "Image url of the product added to the cart") String productImage,
    @McpToolParam(description = "Quantity of the product added to the cart") Integer quantity,
    @McpToolParam(description = "Unit Price of the product added to the cart") BigDecimal unitPrice,
    @McpToolParam(description = "Total Price of the product added to the cart")
        BigDecimal totalPrice,
    @McpToolParam(description = "Time of addition of the product to the cart")
        LocalDateTime addedAt) {}
