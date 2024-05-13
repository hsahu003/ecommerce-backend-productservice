package com.hemendrasahu.productservice.services;

import com.hemendrasahu.productservice.dtos.GenericProductDto;

public interface ProductService {
    public GenericProductDto getProductById(Long id);
    public GenericProductDto createProduct(GenericProductDto genericProductDto);
}
