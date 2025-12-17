package com.hemendrasahu.productservice.services;

import com.hemendrasahu.productservice.dtos.CategoryDto;
import com.hemendrasahu.productservice.models.Category;
import com.hemendrasahu.productservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto createCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return categoryDto;
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with id " + id + " not found"));
    }

    public Category getCategoryByName(String name){
        Category category = categoryRepository.getByName(name);
        return category;
    }
}
