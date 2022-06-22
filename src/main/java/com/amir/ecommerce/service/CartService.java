package com.amir.ecommerce.service;

import com.amir.ecommerce.controller.request.AddToCartRequest;
import com.amir.ecommerce.dto.CartDto;

import java.util.List;

public interface CartService {
    void addToCart(AddToCartRequest addToCartRequest);

    CartDto getCartItems();
}
