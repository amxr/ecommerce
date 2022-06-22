package com.amir.ecommerce.service;

import com.amir.ecommerce.controller.request.CategoryRequest;
import com.amir.ecommerce.model.Category;

import java.util.List;

public interface CategoryService {
    void create(CategoryRequest category);

    List<Category> getCategories();

    void updateCategory(Long categoryId, CategoryRequest category);

    Category getCategory(Long categoryId);

    void deleteCategory(Long categoryId);
}
