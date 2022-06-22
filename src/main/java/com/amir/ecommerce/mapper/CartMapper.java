package com.amir.ecommerce.mapper;

import com.amir.ecommerce.dto.CartItemDto;
import com.amir.ecommerce.model.CartItem;

public class CartMapper {
    public static CartItemDto toCartItemDto(CartItem cartItem){
        return new CartItemDto()
                .setId(cartItem.getId())
                .setProduct(cartItem.getProduct())
                .setQuantity(cartItem.getQuantity());
    }
}
