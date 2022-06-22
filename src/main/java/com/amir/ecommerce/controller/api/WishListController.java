package com.amir.ecommerce.controller.api;

import com.amir.ecommerce.dto.ProductDto;
import com.amir.ecommerce.model.WishListItem;
import com.amir.ecommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {
    @Autowired
    private WishListService wishListService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    private void addWishList(@RequestBody ProductDto productDto){
        wishListService.addToWishList(productDto);
    }

    @GetMapping
    private List<WishListItem> getWishList(){
        return wishListService.getWishList();
    }

    @DeleteMapping("/{itemId}")
    private void removeItem(@PathVariable Long itemId){
        wishListService.removeItem(itemId);
    }
}
