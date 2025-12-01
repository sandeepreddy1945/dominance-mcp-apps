package com.ai.app.model.cas.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record OrderResponseDTO(
    UUID id,
    String status,
    BigDecimal totalAmount,
    String currency,
    String paymentMethod,
    String paymentStatus,
    Map<String, Object> shippingAddress,
    LocalDateTime createdAt,
    List<OrderItemDTO> items) {}
