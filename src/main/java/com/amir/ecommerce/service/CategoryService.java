package com.amir.ecommerce.service;

import com.amir.ecommerce.model.Category;

import java.util.List;

public interface CategoryService {
    void create(Category category);

    List<Category> getCategories();

    void updateCategory(Long categoryID, Category category);
}
