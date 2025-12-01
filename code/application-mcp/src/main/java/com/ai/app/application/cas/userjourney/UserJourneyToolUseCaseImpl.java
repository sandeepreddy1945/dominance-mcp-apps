package com.ai.app.application.cas.userjourney;

import com.ai.app.service.cas.userjourney.UserJourneyToolService;
import com.ai.app.usecase.cas.userjourney.UserJourneyToolUseCase;
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
public class UserJourneyToolUseCaseImpl implements UserJourneyToolUseCase {

  private final UserJourneyToolService userJourneyToolService;

  @Override
  @McpTool(
      name = "completePurchase",
      description = "Complete the purchase of a product in the cart or the entire cart")
  public CallToolResult completePurchase(
      @McpToolParam(
              description =
                  "Product ID in the cart. To be specified only if a single product in the cart needs to be purchased",
              required = false)
          UUID productIdInCart) {
    return CallToolResult.builder()
        .addTextContent(this.userJourneyToolService.completePurchase(productIdInCart))
        .build();
  }
}
