package com.hemendrasahu.productservice.controllers;

import com.hemendrasahu.productservice.dtos.CategoryDto;
import com.hemendrasahu.productservice.models.Category;
import com.hemendrasahu.productservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping()
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.createCategory(categoryDto);
    }

    @GetMapping("{id}")
    public CategoryDto getCategory(@PathVariable("id") String id){
        Category category = categoryService.getCategoryById(id);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}
