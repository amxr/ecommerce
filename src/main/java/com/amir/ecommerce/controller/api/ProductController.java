package com.amir.ecommerce.controller.api;

import com.amir.ecommerce.controller.request.ProductRequest;
import com.amir.ecommerce.dto.ProductDto;
import com.amir.ecommerce.model.Product;
import com.amir.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@Valid @RequestBody ProductRequest productRequest){
        productService.add(productRequest);
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable Long productId){
        return productService.getProduct(productId);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId){
        productService.delete(productId);
    }

    @PutMapping("/update/{produtdID}")
    public void updateProduct(@PathVariable Long productID, @Valid @RequestBody ProductRequest productRequest){
        productService.update(productID, productRequest);
    }
}
