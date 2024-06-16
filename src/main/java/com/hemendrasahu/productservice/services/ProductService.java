package com.hemendrasahu.productservice.services;

import java.util.List;

import com.hemendrasahu.productservice.dtos.GenericProductDto;
import com.hemendrasahu.productservice.exceptions.NotFoundException;

public interface ProductService {
    public GenericProductDto getProductById(Long id) throws NotFoundException;
    public GenericProductDto createProduct(GenericProductDto genericProductDto);
    public List<GenericProductDto> getAllProducts();

    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) throws NotFoundException;

    public GenericProductDto deleteProduct(Long id) throws NotFoundException;
}
