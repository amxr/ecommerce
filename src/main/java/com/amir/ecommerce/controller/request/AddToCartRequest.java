package com.amir.ecommerce.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class AddToCartRequest {
    private Long id;
    private @NotNull Long productId;
    private @NotNull Integer quantity;

}
