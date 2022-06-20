package com.amir.ecommerce.service.impl;

import com.amir.ecommerce.dto.ProductDto;
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
    public void add(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid category")
                );

        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void update(Long productID, ProductDto productDto) {

        if(!productRepository.existsById(productID)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid product id!");
        }

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid category id.")
                );

        Product product = getProductFromDto(productDto, category);
        product.setId(productID);

        productRepository.save(product);
    }

    private Product getProductFromDto(ProductDto productDto, Category category) {
        return new Product()
                .setCategory(category)
                .setDescription(productDto.getDescription())
                .setImageURL(productDto.getImageURL())
                .setName(productDto.getName())
                .setPrice(productDto.getPrice());
    }
}
