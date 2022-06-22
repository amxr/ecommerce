package com.amir.ecommerce.service;

import com.amir.ecommerce.dto.ProductDto;
import com.amir.ecommerce.model.WishListItem;

import java.util.List;

public interface WishListService {
    void addToWishList(ProductDto productDto);

    List<WishListItem> getWishList();

    void removeItem(Long itemId);
}
