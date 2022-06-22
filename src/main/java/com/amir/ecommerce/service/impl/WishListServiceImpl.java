package com.amir.ecommerce.service.impl;

import com.amir.ecommerce.dto.ProductDto;
import com.amir.ecommerce.exceptions.ResourceNotFoundException;
import com.amir.ecommerce.model.Product;
import com.amir.ecommerce.model.User;
import com.amir.ecommerce.model.WishList;
import com.amir.ecommerce.repository.ProductRepository;
import com.amir.ecommerce.repository.UserRepository;
import com.amir.ecommerce.repository.WishListRepository;
import com.amir.ecommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class WishListServiceImpl implements WishListService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addToWishList(ProductDto productDto) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(email.toLowerCase()).orElseThrow();

        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        WishList wishList = new WishList()
                .setProduct(product)
                .setUser(user);

        wishListRepository.save(wishList);
    }
}
