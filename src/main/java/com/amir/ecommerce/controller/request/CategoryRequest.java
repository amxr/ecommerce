package com.amir.ecommerce.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain=true)
public class CategoryRequest {
    private @NotBlank String name;

    private @NotBlank String description;

    private @NotBlank String imageUrl;
}
