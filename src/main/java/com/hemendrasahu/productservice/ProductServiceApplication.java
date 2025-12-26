package com.hemendrasahu.productservice;

import com.hemendrasahu.productservice.models.Category;
import com.hemendrasahu.productservice.models.Order;
import com.hemendrasahu.productservice.models.Product;
import com.hemendrasahu.productservice.repository.CategoryRepository;
import com.hemendrasahu.productservice.repository.OrderRepository;
import com.hemendrasahu.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceApplication(
            ProductRepository productRepository,
            OrderRepository orderRepository,
            CategoryRepository categoryRepository
    ){
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
    }

	public static void main(String[] args) {

        SpringApplication.run(ProductServiceApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {


        System.out.println("Hello World!");

    }
}
