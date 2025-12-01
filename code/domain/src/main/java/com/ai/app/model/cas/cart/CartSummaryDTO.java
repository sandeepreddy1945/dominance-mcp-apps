package com.ai.app.model.cas.cart;

import org.springaicommunity.mcp.annotation.McpToolParam;

public record CartSummaryDTO(
    @McpToolParam(description = "Total number of items in the cart") Integer totalItems,
    @McpToolParam(description = "Number of unique items in the cart") Integer uniqueItems,
    @McpToolParam(description = "Subtotal of the cart") Double subtotal,
    @McpToolParam(description = "Tax amount of the cart") Double taxAmount,
    @McpToolParam(description = "Shipping cost of the cart") Double shippingCost,
    @McpToolParam(description = "Total amount of the cart") Double totalAmount,
    @McpToolParam(description = "Currency of the cart. Default USD") String currency) {}
