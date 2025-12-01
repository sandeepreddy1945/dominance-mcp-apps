package com.ai.app.infra.cas.userjourney;

import com.ai.app.cas.gen.api.UserJourneyApi;
import com.ai.app.service.cas.userjourney.UserJourneyToolService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserJourneyToolServiceImpl implements UserJourneyToolService {

  private final UserJourneyApi userJourneyApi;

  @Override
  public String completePurchase(UUID productIdInCart) {
    if (productIdInCart != null) {
      return this.userJourneyApi
          .purchaseJourneyApiJourneyPurchasePostWithResponseSpec(productIdInCart, false)
          .body(String.class);
    }
    return this.userJourneyApi
        .purchaseJourneyApiJourneyPurchasePostWithResponseSpec(null, true)
        .body(String.class);
  }
}
