package com.amir.ecommerce.controller.api;

import com.amir.ecommerce.controller.request.AddToCartRequest;
import com.amir.ecommerce.dto.CartDto;
import com.amir.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public void addToCart(@RequestBody @Valid AddToCartRequest addToCartRequest){
        cartService.addToCart(addToCartRequest);
    }

    @GetMapping
    public CartDto getCartItems(){
        return cartService.getCartItems();
    }
}
