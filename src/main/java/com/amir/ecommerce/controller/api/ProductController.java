package com.amir.ecommerce.controller.api;

import com.amir.ecommerce.dto.ProductDto;
import com.amir.ecommerce.model.Product;
import com.amir.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@Valid @RequestBody ProductDto productDto){
        productService.add(productDto);
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @PutMapping("/update/{produtdID}")
    public void updateProduct(@PathVariable Long productID, @Valid @RequestBody ProductDto productDto){
        productService.update(productID, productDto);
    }
}
