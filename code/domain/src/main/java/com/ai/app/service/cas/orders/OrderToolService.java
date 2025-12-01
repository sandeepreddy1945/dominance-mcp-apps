package com.ai.app.service.cas.orders;

import com.ai.app.model.cas.order.OrderResponseDTO;
import java.util.List;

public interface OrderToolService {

  OrderResponseDTO checkoutOrder();

  List<OrderResponseDTO> getUserOrders();
}
