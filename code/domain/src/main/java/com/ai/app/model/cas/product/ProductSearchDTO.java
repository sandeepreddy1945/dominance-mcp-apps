package com.ai.app.model.cas.product;

import java.util.List;
import java.util.Map;
import org.springaicommunity.mcp.annotation.McpToolParam;

public record ProductSearchDTO(
    @McpToolParam(description = "List of all the available products") List<ProductDTO> products,
    @McpToolParam(description = "Total Number of products available") Integer total,
    String query,
    Map<String, Object> filters) {}
