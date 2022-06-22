package com.amir.ecommerce.service;

import com.amir.ecommerce.dto.ProductDto;

public interface WishListService {
    void addToWishList(ProductDto productDto);
}
