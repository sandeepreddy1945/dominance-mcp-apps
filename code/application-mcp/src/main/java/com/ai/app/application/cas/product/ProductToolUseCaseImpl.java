package com.ai.app.application.cas.product;

import com.ai.app.model.cas.product.ProductSearchDTO;
import com.ai.app.service.cas.product.ProductToolService;
import com.ai.app.usecase.cas.product.ProductToolUseCase;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.ElicitResult.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springaicommunity.mcp.context.McpSyncRequestContext;
import org.springaicommunity.mcp.context.StructuredElicitResult;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductToolUseCaseImpl implements ProductToolUseCase {

  private final ProductToolService productToolService;

  @McpTool(
      name = "getProductDetails",
      description = "Get product details by different filter criteria")
  @Override
  public CallToolResult getProductDetails(
      McpSyncRequestContext context,
      @McpToolParam(
              description =
                  "List of product categories. A comma separated list of values to be provided",
              required = false)
          String categories,
      @McpToolParam(description = "Boolean value to filter featured products", required = false)
          Boolean isFeatured,
      @McpToolParam(description = "Product name to filter", required = false) String productName,
      @McpToolParam(description = "Product ID to filter", required = false) String productId,
      @McpToolParam(
              description = "Boolean value to filter recommended products for the user",
              required = false)
          Boolean recommendedForMe) {
    if (StringUtils.isBlank(categories)
        && StringUtils.isBlank(productName)
        && StringUtils.isBlank(productId)) {
      // produce an elicitation requesting for one of these details
      if (context.elicitEnabled()) {
        StructuredElicitResult<UpdateProductDetailsRequest> elicit =
            context.elicit(UpdateProductDetailsRequest.class);
        if (elicit.action() == Action.DECLINE || elicit.action() == Action.CANCEL) {
          log.info("User declined / cancelled the elicitation request !");
          return null;
          // if its proper action just continue on the flow.
        }
        UpdateProductDetailsRequest request = elicit.structuredContent();
        categories = request.getCategories();
        productName = request.getProductName();
        productId = request.getProductId();
      } else {
        throw new UnsupportedOperationException(
            "One of the product details is required! Provide the product category, product name or product ID");
      }
    }

    ProductSearchDTO allProducts =
        this.productToolService.getAllProducts(categories, productName, productId);
    return CallToolResult.builder().structuredContent(allProducts).build();
  }

  @McpTool(name = "getAllProductCategories", description = "Get all product categories")
  @Override
  public CallToolResult getAllProductCategories() {
    return CallToolResult.builder()
        .addTextContent(String.join(", ", this.productToolService.getAllProductCategories()))
        .build();
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  static class UpdateProductDetailsRequest {
    @JsonProperty String categories;
    @JsonProperty String productName;
    @JsonProperty String productId;
  }
}
