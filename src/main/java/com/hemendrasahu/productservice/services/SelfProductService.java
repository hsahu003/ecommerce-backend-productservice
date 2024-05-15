package com.hemendrasahu.productservice.services;

import com.hemendrasahu.productservice.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return List.of();
    }

    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto){
        return null;
    }

}
