package com.amir.ecommerce.service.impl;

import com.amir.ecommerce.controller.request.AddToCartRequest;
import com.amir.ecommerce.dto.CartDto;
import com.amir.ecommerce.dto.CartItemDto;
import com.amir.ecommerce.mapper.CartMapper;
import com.amir.ecommerce.exceptions.ResourceNotFoundException;
import com.amir.ecommerce.model.CartItem;
import com.amir.ecommerce.model.Product;
import com.amir.ecommerce.model.User;
import com.amir.ecommerce.repository.CartRepository;
import com.amir.ecommerce.repository.ProductRepository;
import com.amir.ecommerce.repository.UserRepository;
import com.amir.ecommerce.service.CartService;
import com.amir.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    @Override
    public void addToCart(AddToCartRequest addToCartRequest) {
        User user = userService.getUser();

        Product product = productRepository.findById(addToCartRequest.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        CartItem cartItem = new CartItem()
                .setProduct(product)
                .setUser(user)
                .setQuantity(addToCartRequest.getQuantity());

        cartRepository.save(cartItem);
    }

    @Override
    public CartDto getCartItems() {
        User user = userService.getUser();

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
