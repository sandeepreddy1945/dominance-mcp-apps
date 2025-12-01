package com.ai.app.infra.cas.order;

import com.ai.app.cas.gen.model.OrderResponse;
import com.ai.app.model.cas.order.OrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface OrderToolMapper {

  OrderResponseDTO toOrderResponseDTO(OrderResponse orderResponse);
}
