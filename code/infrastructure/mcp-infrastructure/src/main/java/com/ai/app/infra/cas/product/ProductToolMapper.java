package com.ai.app.infra.cas.product;

import com.ai.app.cas.gen.model.ProductResponse;
import com.ai.app.cas.gen.model.ProductSearchResponse;
import com.ai.app.model.cas.product.ProductDTO;
import com.ai.app.model.cas.product.ProductSearchDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface ProductToolMapper {

  ProductDTO toProductDTO(ProductResponse response);

  ProductSearchDTO toProductSearchDTO(ProductSearchResponse productSearchResponse);
}
