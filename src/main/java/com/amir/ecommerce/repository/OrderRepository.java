package com.amir.ecommerce.repository;

import com.amir.ecommerce.model.Order;
import com.amir.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserOrderByCreatedDateDesc(User user);
}
