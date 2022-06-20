package com.amir.ecommerce.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class ProductDto {
    private Long id;
    private @NotBlank String name;
    private @NotBlank String imageURL;
    private @NotNull double price;
    private @NotBlank String description;
    private @NotNull Long categoryId;
}
