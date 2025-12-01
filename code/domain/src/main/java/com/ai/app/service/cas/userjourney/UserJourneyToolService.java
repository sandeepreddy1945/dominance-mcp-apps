package com.ai.app.service.cas.userjourney;

import java.util.UUID;

public interface UserJourneyToolService {

  String completePurchase(UUID productIdInCart);
}
