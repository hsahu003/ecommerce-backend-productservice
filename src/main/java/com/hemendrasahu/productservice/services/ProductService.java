package com.hemendrasahu.productservice.services;

import java.util.List;

import com.hemendrasahu.productservice.dtos.ProductRequestDto;
import com.hemendrasahu.productservice.dtos.ProductResponseDto;
import com.hemendrasahu.productservice.exceptions.NotFoundException;

public interface ProductService {
    ProductResponseDto getProductById(String id) throws NotFoundException;
    ProductResponseDto createProduct(ProductRequestDto productRequestDto) throws NotFoundException;
    List<ProductResponseDto> getAllProducts();

    ProductResponseDto updateProductById(String id, ProductRequestDto productRequestDto) throws NotFoundException;

    ProductResponseDto deleteProduct(String id) throws NotFoundException;
}
