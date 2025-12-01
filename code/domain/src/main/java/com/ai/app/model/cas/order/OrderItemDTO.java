package com.ai.app.model.cas.order;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemDTO(
    UUID id,
    UUID productId,
    String productName,
    String productCode,
    Integer quantity,
    BigDecimal unitPrice,
    BigDecimal totalPrice) {}
