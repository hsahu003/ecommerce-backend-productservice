package com.hemendrasahu.productservice.repository;

import com.hemendrasahu.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
