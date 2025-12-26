package com.hemendrasahu.productservice.services;

import java.util.List;

import com.hemendrasahu.productservice.dtos.ProductRequestDto;
import com.hemendrasahu.productservice.dtos.ProductResponseDto;
import com.hemendrasahu.productservice.exceptions.NotFoundException;

public interface ProductService {
    public ProductResponseDto getProductById(String id) throws NotFoundException;
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto);
    public List<ProductResponseDto> getAllProducts();

    public ProductResponseDto updateProductById(String id, ProductRequestDto productRequestDto) throws NotFoundException;

    public ProductResponseDto deleteProduct(String id) throws NotFoundException;
}
