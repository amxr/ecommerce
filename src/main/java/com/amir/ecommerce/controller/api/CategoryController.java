package com.amir.ecommerce.controller.api;

import com.amir.ecommerce.model.Category;
import com.amir.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody Category category){
        categoryService.create(category);
    }

    @GetMapping
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @PutMapping("/update/{categoryId}")
    public void updateCategory(@PathVariable("categoryID") Long categoryID, @Valid @RequestBody Category category){
        categoryService.updateCategory(categoryID, category);
    }
}
