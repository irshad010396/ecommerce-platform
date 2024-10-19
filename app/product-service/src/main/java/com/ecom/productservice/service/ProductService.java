package com.ecom.productservice.service;

import com.ecom.productservice.dto.ProductDto;

public interface ProductService {
    String addProduct(ProductDto productDto);

    ProductDto getProduct(String id);

    String updateProduct(ProductDto productDto);

    String deleteProduct(String  id);
}
