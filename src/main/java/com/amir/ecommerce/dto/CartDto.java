package com.amir.ecommerce.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CartDto {
    //list of each item
    private List<CartItemDto> cartItems;

    // total cost for the cart
    private double totalCost;
}
