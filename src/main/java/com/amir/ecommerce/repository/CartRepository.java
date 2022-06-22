package com.amir.ecommerce.repository;

import com.amir.ecommerce.model.CartItem;
import com.amir.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByUserOrderByCreatedDateDesc(User user);
}
