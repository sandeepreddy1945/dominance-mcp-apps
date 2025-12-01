package com.ai.app.service.cas.product;

import com.ai.app.model.cas.product.ProductSearchDTO;
import java.util.List;

public interface ProductToolService {
  List<String> getAllProductCategories();

  ProductSearchDTO getAllProducts(String category, String productName, String productId);
}
