package com.hemendrasahu.productservice.services;

import com.hemendrasahu.productservice.dtos.CategoryDto;
import com.hemendrasahu.productservice.models.Category;
import com.hemendrasahu.productservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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
        Optional<Category> optional = categoryRepository.findById(id);
        return optional.get();
    }

    public Category getCategoryByName(String name){
        Category category = categoryRepository.getByName(name);
        return category;
    }
}
