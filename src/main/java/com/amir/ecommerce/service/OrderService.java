package com.amir.ecommerce.service;

import com.amir.ecommerce.dto.CheckoutItemDto;
import com.amir.ecommerce.dto.StripeResponse;
import com.amir.ecommerce.model.Order;
import com.stripe.exception.StripeException;

import java.util.List;

public interface OrderService {
    StripeResponse checkout(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException;

    void placeOrder(String sessionId);

    List<Order> getAllOrders();

    Order getOrder(Long orderId);
}
