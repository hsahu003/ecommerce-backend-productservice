package com.hemendrasahu.productservice.controllers;

import com.hemendrasahu.productservice.dtos.GenericProductDto;
import com.hemendrasahu.productservice.dtos.ProductRequestDto;
import com.hemendrasahu.productservice.dtos.ProductResponseDto;
import com.hemendrasahu.productservice.exceptions.NotFoundException;
import com.hemendrasahu.productservice.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<ProductResponseDto> getAllProducts(){
        return this.productService.getAllProducts();
    }

    @GetMapping("{id}")
    public ProductResponseDto getProductById(@PathVariable("id") String id) throws NotFoundException {
        return this.productService.getProductById(id);

    }

    @PatchMapping("{id}")
    public ProductResponseDto updateProductById(@PathVariable("id") String id, @RequestBody ProductRequestDto productRequestDto) throws NotFoundException{
        return this.productService.updateProductById(id, productRequestDto);
    }

    @PostMapping()
    public ProductResponseDto createProduct(@Valid @RequestBody ProductRequestDto productRequestDto){
        return productService.createProduct(productRequestDto);
    }

    @DeleteMapping("{id}")
    public ProductResponseDto deleteProduct(@PathVariable("id") String id) throws NotFoundException{
        return productService.deleteProduct(id);
    }
}
