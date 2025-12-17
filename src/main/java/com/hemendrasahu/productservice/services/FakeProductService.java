package com.hemendrasahu.productservice.services;

import com.hemendrasahu.productservice.exceptions.NotFoundException;
import com.hemendrasahu.productservice.mappers.ProductDtoMapper;
import com.hemendrasahu.productservice.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;
import com.hemendrasahu.productservice.dtos.GenericProductDto;
import com.hemendrasahu.productservice.thirdpartyclients.fakestore.FakeStoreProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Primary
@Service("fakeProductService")
public class FakeProductService implements ProductService{

    private FakeStoreProductClient fakeStoreProductClient;
    private ProductDtoMapper productDtoMapper;
    private String productUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductUrl = "https://fakestoreapi.com/products";
    private String getAllProductUrl =  "https://fakestoreapi.com/products";

    @Autowired
    public FakeProductService(FakeStoreProductClient fakeStoreProductClient, ProductDtoMapper productDtoMapper){
        this.fakeStoreProductClient = fakeStoreProductClient;
        this.productDtoMapper = productDtoMapper;
    }

    private GenericProductDto convertFakeStoreDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        if(fakeStoreProductDto == null) return null;
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());

        return genericProductDto;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return productDtoMapper.toGeneric(fakeStoreProductClient.getProductById(id));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        FakeStoreProductDto fakeStoreProductDto = productDtoMapper.toFakeStore(genericProductDto);
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.createProduct(fakeStoreProductDto));
    }

    @Override
    public List<GenericProductDto> getAllProducts(){
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreProductClient.getAllProducts();
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            genericProductDtos.add(productDtoMapper.toGeneric(fakeStoreProductDto));
        }
        return genericProductDtos;
    }

    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto){
        FakeStoreProductDto fakeStoreProductDto = productDtoMapper.toFakeStore(genericProductDto);
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.updateProductById(id, fakeStoreProductDto));
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return productDtoMapper.toGeneric(fakeStoreProductClient.deleteProduct(id));
    }
}
