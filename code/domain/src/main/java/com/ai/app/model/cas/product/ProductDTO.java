package com.ai.app.model.cas.product;

import java.math.BigDecimal;
import java.util.UUID;
import org.springaicommunity.mcp.annotation.McpToolParam;

public record ProductDTO(
    @McpToolParam(description = "Unique Id related to the product") UUID id,
    @McpToolParam(description = "Code related to the product") String code,
    @McpToolParam(description = "Unique Product Name of the Product") String name,
    @McpToolParam(description = "Description of the product") String description,
    @McpToolParam(description = "The category to which the product belongs") String category,
    @McpToolParam(description = "The price of the Product") BigDecimal price,
    @McpToolParam(description = "Flag indicating weather the product is in stock or not")
        Boolean inStock,
    @McpToolParam(description = "Image url of the product") String imageUrl) {}
