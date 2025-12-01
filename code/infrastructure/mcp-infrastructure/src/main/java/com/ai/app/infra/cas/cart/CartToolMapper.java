package com.ai.app.infra.cas.cart;

import com.ai.app.cas.gen.model.AddToCartRequest;
import com.ai.app.cas.gen.model.CartItemResponse;
import com.ai.app.cas.gen.model.CartResponse;
import com.ai.app.infra.cas.cart.model.CartSummary;
import com.ai.app.model.cas.cart.CartItemDTO;
import com.ai.app.model.cas.cart.CartResponseDTO;
import com.ai.app.model.cas.cart.CartSummaryDTO;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface CartToolMapper {

  AddToCartRequest toCartItemAddRequest(UUID productId, Integer quantity);

  CartItemDTO toCartItemDTO(CartItemResponse cartItemResponse);

  CartSummaryDTO toCartSummaryDTO(CartSummary cartSummary);

  CartResponseDTO toCartResponseDTO(CartResponse cartApiCartGet);
}
