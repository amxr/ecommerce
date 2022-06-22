package com.amir.ecommerce.service.impl;

import com.amir.ecommerce.dto.ProductDto;
import com.amir.ecommerce.exceptions.ResourceNotFoundException;
import com.amir.ecommerce.model.Product;
import com.amir.ecommerce.model.User;
import com.amir.ecommerce.model.WishListItem;
import com.amir.ecommerce.repository.ProductRepository;
import com.amir.ecommerce.repository.WishListItemRepository;
import com.amir.ecommerce.service.UserService;
import com.amir.ecommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WishListServiceImpl implements WishListService {
    @Autowired
    private WishListItemRepository wishListItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addToWishList(ProductDto productDto) {
        User user = userService.getUser();

        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        WishListItem wishListItem = new WishListItem()
                .setProduct(product)
                .setUser(user);

        wishListItemRepository.save(wishListItem);
    }

    @Override
    public List<WishListItem> getWishList() {
        User user = userService.getUser();
        return wishListItemRepository.findAllByUserOrderByCreatedDateDesc(user);
    }

    @Override
    public void removeItem(Long itemId) {
        User user = userService.getUser();

        WishListItem wishListItem = wishListItemRepository.findById(itemId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Invalied item id")
                );

        if(!wishListItem.getUser().equals(user)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Can't access item.");
        }

        wishListItemRepository.delete(wishListItem);
    }
}
