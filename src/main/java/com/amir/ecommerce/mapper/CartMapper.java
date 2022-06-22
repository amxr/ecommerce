package com.amir.ecommerce.mapper;

import com.amir.ecommerce.dto.CartDto;
import com.amir.ecommerce.dto.CartItemDto;
import com.amir.ecommerce.model.Cart;

public class CartMapper {
    public static CartItemDto toCartItemDto(Cart cart){
        return new CartItemDto()
                .setId(cart.getId())
                .setProduct(cart.getProduct())
                .setQuantity(cart.getQuantity());
    }
}
