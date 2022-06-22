package com.amir.ecommerce.service.impl;

import com.amir.ecommerce.controller.request.ProductRequest;
import com.amir.ecommerce.dto.ProductDto;
import com.amir.ecommerce.exceptions.ResourceNotFoundException;
import com.amir.ecommerce.mapper.ProductMapper;
import com.amir.ecommerce.model.Category;
import com.amir.ecommerce.model.Product;
import com.amir.ecommerce.repository.CategoryRepository;
import com.amir.ecommerce.repository.ProductRepository;
import com.amir.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void add(ProductRequest productRequest) {
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category not found.")
                );

        Product product = ProductMapper.toProduct(productRequest);
        product.setCategory(category);

        productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void update(Long productID, ProductRequest productRequest) {

        Product product = productRepository.findById(productID)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Invalid product id!")
                );

        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category not found.")
                );

        ProductMapper.updateProduct(product, productRequest);
        product.setCategory(category);

        productRepository.save(product);
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product not found!")
                );
    }

    @Override
    public void delete(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Invalid product id!")
                );

        productRepository.delete(product);
    }

}
