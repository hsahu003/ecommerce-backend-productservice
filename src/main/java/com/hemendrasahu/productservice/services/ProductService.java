package com.hemendrasahu.productservice.services;

import java.util.List;

import com.hemendrasahu.productservice.dtos.GenericProductDto;

public interface ProductService {
    public GenericProductDto getProductById(Long id);
    public GenericProductDto createProduct(GenericProductDto genericProductDto);
    public List<GenericProductDto> getAllProducts();

    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto);
}
