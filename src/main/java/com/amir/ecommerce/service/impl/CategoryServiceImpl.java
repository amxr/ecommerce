package com.amir.ecommerce.service.impl;

import com.amir.ecommerce.controller.request.CategoryRequest;
import com.amir.ecommerce.exceptions.ResourceNotFoundException;
import com.amir.ecommerce.mapper.CategoryMapper;
import com.amir.ecommerce.model.Category;
import com.amir.ecommerce.repository.CategoryRepository;
import com.amir.ecommerce.repository.ProductRepository;
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

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void create(CategoryRequest categoryRequest) {
        if(categoryRepository.findByName(categoryRequest.getName()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists");
        }
        Category category = CategoryMapper.toCategory(categoryRequest);
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Invalid category id.")
                );
        CategoryMapper.updateCategory(category, categoryRequest);
        categoryRepository.save(category);
    }

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category not found.")
                );
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Invalid category id.")
                );
        productRepository.deleteAllByCategory(category);
        categoryRepository.delete(category);
    }
}
