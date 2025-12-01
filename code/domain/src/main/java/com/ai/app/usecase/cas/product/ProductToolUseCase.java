package com.ai.app.usecase.cas.product;

import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import org.springaicommunity.mcp.context.McpSyncRequestContext;

public interface ProductToolUseCase {

  CallToolResult getProductDetails(
      McpSyncRequestContext context,
      String categories,
      Boolean isFeatured,
      String productName,
      String productId,
      Boolean recommendedForMe);

  CallToolResult getAllProductCategories();
}
