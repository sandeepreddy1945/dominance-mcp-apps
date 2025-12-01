package com.ai.app.infra.cas.order;

import com.ai.app.cas.gen.api.OrdersApi;
import com.ai.app.cas.gen.model.CheckoutRequest;
import com.ai.app.cas.gen.model.OrderResponse;
import com.ai.app.model.cas.order.OrderResponseDTO;
import com.ai.app.service.cas.orders.OrderToolService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderToolServiceImpl implements OrderToolService {

  private final OrdersApi ordersApi;

  private final OrderToolMapper orderToolMapper;

  @Override
  public OrderResponseDTO checkoutOrder() {
    return this.orderToolMapper.toOrderResponseDTO(
        this.ordersApi.checkoutApiOrdersCheckoutPost(new CheckoutRequest()));
  }

  @Override
  public List<OrderResponseDTO> getUserOrders() {
    List<OrderResponse> userOrders = this.ordersApi.getUserOrdersApiOrdersGet(null);
    return userOrders.stream().map(this.orderToolMapper::toOrderResponseDTO).toList();
  }
}
