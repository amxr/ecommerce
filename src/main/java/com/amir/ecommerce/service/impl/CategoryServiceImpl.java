package com.amir.ecommerce.service.impl;

import com.amir.ecommerce.model.Category;
import com.amir.ecommerce.repository.CategoryRepository;
import com.amir.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void create(Category category) {
        if(categoryRepository.findByName(category.getName()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists");
        }
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void updateCategory(Long categoryID, Category category) {
        Category category1 = categoryRepository.findById(categoryID)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid category id.")
                );

        category1
                .setName(category.getName())
                .setDescription(category.getDescription())
                .setImageUrl(category.getImageUrl());

        categoryRepository.save(category1);
    }
}
