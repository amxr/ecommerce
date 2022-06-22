package com.amir.ecommerce.service.impl;

import com.amir.ecommerce.dto.CartDto;
import com.amir.ecommerce.dto.CartItemDto;
import com.amir.ecommerce.dto.CheckoutItemDto;
import com.amir.ecommerce.dto.StripeResponse;
import com.amir.ecommerce.exceptions.ResourceNotFoundException;
import com.amir.ecommerce.model.Order;
import com.amir.ecommerce.model.OrderItem;
import com.amir.ecommerce.model.User;
import com.amir.ecommerce.repository.OrderItemRepository;
import com.amir.ecommerce.repository.OrderRepository;
import com.amir.ecommerce.repository.UserRepository;
import com.amir.ecommerce.service.CartService;
import com.amir.ecommerce.service.OrderService;
import com.amir.ecommerce.service.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Value("${BASE_URL}")
    private String baseURL;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;

    @Override
    public StripeResponse checkout(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        Session session = createSession(checkoutItemDtoList);
        StripeResponse stripeResponse = new StripeResponse();
        stripeResponse.setSessionId(session.getId());
        return stripeResponse;
    }

    @Override
    public void placeOrder(String sessionId) {
        User user = userService.getUser();

        CartDto cartDto = cartService.getCartItems();

        Order newOrder = new Order()
                .setSessionId(sessionId)
                .setUser(user)
                .setTotalPrice(cartDto.getTotalCost());

        Order savedOrder = orderRepository.save(newOrder);

        for(CartItemDto cartItemDto : cartDto.getCartItems()){
            OrderItem orderItem = new OrderItem()
                    .setPrice(cartItemDto.getProduct().getPrice())
                    .setProduct(cartItemDto.getProduct())
                    .setQuantity(cartItemDto.getQuantity())
                    .setOrder(savedOrder);

            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        User user = userService.getUser();

        return orderRepository.findAllByUserOrderByCreatedDateDesc(user);
    }

    @Override
    public Order getOrder(Long orderId) {
        User user = userService.getUser();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Order not found.")
                );

        if(!order.getUser().equals(user)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This order does not belong to you.");
        }

        return order;
    }

    // create total price and send product name as input
    SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount( ((long) checkoutItemDto.getPrice()) * 100)
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDto.getProductName())
                                .build())
                .build();
    }

    // build each product in the stripe checkout page
    SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.builder()
                // set price for each product
                .setPriceData(createPriceData(checkoutItemDto))
                // set quantity for each product
                .setQuantity(Long.parseLong(String.valueOf(checkoutItemDto.getQuantity())))
                .build();
    }

    public Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {

        // supply success and failure url for stripe
        String successURL = baseURL + "payment/success";
        String failedURL = baseURL + "payment/failed";

        // set the private key
        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemsList = new ArrayList<>();

        // for each product compute SessionCreateParams.LineItem
        for (CheckoutItemDto checkoutItemDto : checkoutItemDtoList) {
            sessionItemsList.add(createSessionLineItem(checkoutItemDto));
        }

        // build the session param
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failedURL)
                .addAllLineItem(sessionItemsList)
                .setSuccessUrl(successURL)
                .build();
        return Session.create(params);
    }
}
