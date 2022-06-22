package com.amir.ecommerce.controller.api;

import com.amir.ecommerce.controller.request.CategoryRequest;
import com.amir.ecommerce.model.Category;
import com.amir.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CategoryRequest categoryRequest){
        categoryService.create(categoryRequest);
    }

    @GetMapping
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @PutMapping("/update/{categoryId}")
    public void updateCategory(@PathVariable("categoryID") Long categoryID, @Valid @RequestBody CategoryRequest categoryRequest){
        categoryService.updateCategory(categoryID, categoryRequest);
    }
}
