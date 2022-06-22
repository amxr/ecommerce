package com.amir.ecommerce.service.impl;

import com.amir.ecommerce.controller.request.AddToCartRequest;
import com.amir.ecommerce.dto.CartDto;
import com.amir.ecommerce.dto.CartItemDto;
import com.amir.ecommerce.mapper.CartMapper;
import com.amir.ecommerce.exceptions.ResourceNotFoundException;
import com.amir.ecommerce.model.Cart;
import com.amir.ecommerce.model.Product;
import com.amir.ecommerce.model.User;
import com.amir.ecommerce.repository.CartRepository;
import com.amir.ecommerce.repository.ProductRepository;
import com.amir.ecommerce.repository.UserRepository;
import com.amir.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addToCart(AddToCartRequest addToCartRequest) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(email.toLowerCase()).orElseThrow();

        Product product = productRepository.findById(addToCartRequest.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Cart cart = new Cart()
                .setProduct(product)
                .setUser(user)
                .setQuantity(addToCartRequest.getQuantity());

        cartRepository.save(cart);
    }

    @Override
    public CartDto getCartItems() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(email.toLowerCase()).orElseThrow();

        List<CartItemDto> cartItems = cartRepository
                .findAllByUserOrderByCreatedDateDesc(user)
                .stream()
                .map(CartMapper::toCartItemDto
                ).toList();

        double totalCost = 0;
        for(CartItemDto c : cartItems){
            totalCost += c.getQuantity() * c.getProduct().getPrice();
        }

        return new CartDto()
                .setCartItems(cartItems)
                .setTotalCost(totalCost);
    }
}
