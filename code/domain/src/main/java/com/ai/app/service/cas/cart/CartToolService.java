package com.ai.app.service.cas.cart;

import com.ai.app.model.cas.cart.CartItemDTO;
import com.ai.app.model.cas.cart.CartResponseDTO;
import com.ai.app.model.cas.cart.CartSummaryDTO;
import java.util.UUID;

public interface CartToolService {

  CartItemDTO addItemToCart(UUID productId, Integer quantity);

  CartSummaryDTO getCartSummary();

  CartResponseDTO getCurrentCartItemDetails();
}
