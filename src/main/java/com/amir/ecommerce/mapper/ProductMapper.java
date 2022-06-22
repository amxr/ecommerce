package com.amir.ecommerce.mapper;

import com.amir.ecommerce.controller.request.ProductRequest;
import com.amir.ecommerce.model.Product;

public class ProductMapper {
    public static Product toProduct(ProductRequest productRequest){
        return new Product()
                .setDescription(productRequest.getDescription())
                .setImageURL(productRequest.getImageURL())
                .setName(productRequest.getName())
                .setPrice(productRequest.getPrice());
    }

    public static void updateProduct(Product product, ProductRequest productRequest) {
        product
                .setDescription(productRequest.getDescription())
                .setImageURL(productRequest.getImageURL())
                .setName(productRequest.getName())
                .setPrice(productRequest.getPrice());
    }
}
