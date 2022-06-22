package com.amir.ecommerce.service;

import com.amir.ecommerce.controller.request.ProductRequest;
import com.amir.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    void add(ProductRequest productRequest);

    List<Product> getProducts();

    void update(Long productID, ProductRequest productRequest);

    Product getProduct(Long productId);

    void delete(Long productId);
}
