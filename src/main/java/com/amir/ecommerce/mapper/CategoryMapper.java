package com.amir.ecommerce.mapper;

import com.amir.ecommerce.controller.request.CategoryRequest;
import com.amir.ecommerce.model.Category;

public class CategoryMapper {
    public static Category toCategory(CategoryRequest categoryRequest){
        return new Category()
                .setName(categoryRequest.getName())
                .setDescription(categoryRequest.getDescription())
                .setImageUrl(categoryRequest.getImageUrl());
    }

    public static void updateCategory(Category category, CategoryRequest categoryRequest){
        category
                .setName(category.getName())
                .setDescription(category.getDescription())
                .setImageUrl(category.getImageUrl());
    }
}
