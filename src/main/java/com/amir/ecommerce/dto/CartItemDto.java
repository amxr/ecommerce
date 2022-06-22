package com.amir.ecommerce.dto;

import com.amir.ecommerce.model.Product;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CartItemDto {
    private Long id;
    private Integer quantity;
    private Product product;
}
