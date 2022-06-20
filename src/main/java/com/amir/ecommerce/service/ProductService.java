package com.amir.ecommerce.service;

import com.amir.ecommerce.dto.ProductDto;
import com.amir.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    void add(ProductDto productDto);

    List<Product> getProducts();

    void update(Long productID, ProductDto productDto);
}
