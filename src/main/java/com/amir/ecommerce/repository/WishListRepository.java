package com.amir.ecommerce.repository;

import com.amir.ecommerce.model.User;
import com.amir.ecommerce.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
