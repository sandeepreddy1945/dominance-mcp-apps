package com.ai.app.infra.cas.cart;

import com.ai.app.cas.gen.api.CartApi;
import com.ai.app.infra.cas.cart.model.CartSummary;
import com.ai.app.model.cas.cart.CartItemDTO;
import com.ai.app.model.cas.cart.CartResponseDTO;
import com.ai.app.model.cas.cart.CartSummaryDTO;
import com.ai.app.service.cas.cart.CartToolService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient.ResponseSpec;

@Service
@RequiredArgsConstructor
public class CartToolServiceImpl implements CartToolService {

  private final CartApi cartApi;

  private final CartToolMapper cartToolMapper;

  @Override
  public CartItemDTO addItemToCart(UUID productId, Integer quantity) {
    return cartToolMapper.toCartItemDTO(
        cartApi.addToCartApiCartAddPost(
            this.cartToolMapper.toCartItemAddRequest(productId, quantity)));
  }

  @Override
  public CartSummaryDTO getCartSummary() {
    ResponseSpec cartSummarySpec = cartApi.getCartSummaryApiCartSummaryGetWithResponseSpec();
    ParameterizedTypeReference<CartSummary> typeReference = new ParameterizedTypeReference<>() {};
    CartSummary cartSummary = cartSummarySpec.body(typeReference);
    return cartToolMapper.toCartSummaryDTO(cartSummary);
  }

  @Override
  public CartResponseDTO getCurrentCartItemDetails() {
    return cartToolMapper.toCartResponseDTO(cartApi.getCartApiCartGet());
  }
}
