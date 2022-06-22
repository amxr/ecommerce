package com.amir.ecommerce.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CheckoutItemDto {
    private String productName;
    private int  quantity;
    private double price;
    private long productId;
}
