package com.ai.app.infra.cas.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartSummary {
  @JsonProperty("total_items")
  private Integer totalItems;

  @JsonProperty("unique_items")
  private Integer uniqueItems;

  @JsonProperty("subtotal")
  private Double subtotal;

  @JsonProperty("tax_amount")
  private Double taxAmount;

  @JsonProperty("shipping_cost")
  private Double shippingCost;

  @JsonProperty("total_amount")
  private Double totalAmount;

  @JsonProperty("currency")
  private String currency;

  @JsonProperty("free_shipping_threshold")
  private Integer freeShippingThreshold;

  @JsonProperty("free_shipping_remaining")
  private Integer freeShippingRemaining;
}
