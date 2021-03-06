package com.amir.ecommerce.repository;

import com.amir.ecommerce.model.Category;
import com.amir.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteAllByCategory(Category category);
}
