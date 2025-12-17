package com.hemendrasahu.productservice.repository;

import com.hemendrasahu.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Category getByName(String name);
}
