package com.ai.app.infra.cas.product;

import com.ai.app.cas.gen.api.ProductsApi;
import com.ai.app.cas.gen.model.ProductResponse;
import com.ai.app.cas.gen.model.ProductSearchResponse;
import com.ai.app.exception.product.ProductNotFoundException;
import com.ai.app.infra.cas.product.model.Categories;
import com.ai.app.model.cas.product.ProductSearchDTO;
import com.ai.app.service.cas.product.ProductToolService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient.ResponseSpec;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductToolServiceImpl implements ProductToolService {

  private static final String EN_LANG = "en";

  private final ProductsApi productsApi;

  private final ProductToolMapper productToolMapper;

  @Override
  public List<String> getAllProductCategories() {
    ResponseSpec responseSpec =
        this.productsApi.getProductCategoriesApiProductsCategoriesGetWithResponseSpec(EN_LANG);
    ParameterizedTypeReference<Categories> typeRef = new ParameterizedTypeReference<>() {};
    Categories categories = responseSpec.body(typeRef);
    return Optional.ofNullable(categories).map(Categories::getCategories).orElse(List.of());
  }

  @Override
  public ProductSearchDTO getAllProducts(String category, String productName, String productId) {
    if (StringUtils.isNotEmpty(productId)) {
      ProductResponse response =
          this.productsApi.getProductApiProductsProductIdGet(UUID.fromString(productId), EN_LANG);
      if (response == null) {
        throw new ProductNotFoundException("Product not found with id: " + productId);
      }

      return new ProductSearchDTO(
          List.of(this.productToolMapper.toProductDTO(response)), 1, null, null);
    }

    if (StringUtils.isNotEmpty(productName)) {
      ProductSearchResponse productSearchResponse =
          this.productsApi.searchProductsApiProductsSearchGet(
              productName, category, null, null, EN_LANG, null);
      return this.productToolMapper.toProductSearchDTO(productSearchResponse);
    }
    return this.productToolMapper.toProductSearchDTO(
        this.productsApi.listProductsApiProductsGet(
            null, null, category, null, null, null, null, EN_LANG, null));
  }
}
