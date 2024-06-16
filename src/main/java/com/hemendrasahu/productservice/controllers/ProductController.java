package com.hemendrasahu.productservice.controllers;

import com.hemendrasahu.productservice.dtos.ExceptionDto;
import com.hemendrasahu.productservice.dtos.GenericProductDto;
import com.hemendrasahu.productservice.exceptions.NotFoundException;
import com.hemendrasahu.productservice.models.Product;
import com.hemendrasahu.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public List<GenericProductDto> getAllProducts(){
        return this.productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return this.productService.getProductById(id);

    }

    @PatchMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto genericProductDto) throws NotFoundException{
        return this.productService.updateProductById(id, genericProductDto);
    }

    @PostMapping()
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
        return productService.createProduct(genericProductDto);
    }

    @DeleteMapping("{id}")
    public GenericProductDto deleteProduct(@PathVariable("id") Long id) throws NotFoundException{
        return productService.deleteProduct(id);
    }
}
