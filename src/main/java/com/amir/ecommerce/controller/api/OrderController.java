package com.amir.ecommerce.controller.api;

import com.amir.ecommerce.dto.CheckoutItemDto;
import com.amir.ecommerce.dto.StripeResponse;
import com.amir.ecommerce.model.Order;
import com.amir.ecommerce.service.OrderService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    // stripe create session API
    @PostMapping("/create-checkout-session")
    public StripeResponse checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        return orderService.checkout(checkoutItemDtoList);
    }

    @PostMapping("/add")
    public void placeOrder(@RequestParam("sessionId") String sessionId){
        orderService.placeOrder(sessionId);
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
}
