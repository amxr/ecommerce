package com.amir.ecommerce.repository;

import com.amir.ecommerce.model.User;
import com.amir.ecommerce.model.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {
    List<WishListItem> findAllByUserOrderByCreatedDateDesc(User user);
}
