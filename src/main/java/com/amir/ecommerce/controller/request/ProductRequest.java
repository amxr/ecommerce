package com.amir.ecommerce.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class ProductRequest {
    private @NotBlank String name;
    private @NotBlank String imageURL;
    private @NotNull double price;
    private @NotBlank String description;
    private @NotNull Long categoryId;
}
