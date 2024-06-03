package com.hemendrasahu.productservice.services;

import com.hemendrasahu.productservice.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;
import com.hemendrasahu.productservice.dtos.GenericProductDto;
import com.hemendrasahu.productservice.thirdpartyclients.fakestore.FakeStoreProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("fakeProductService")
public class FakeProductService implements ProductService{

    private FakeStoreProductClient fakeStoreProductClient;
    private RestTemplateBuilder restTemplateBuilder;
    private String productUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductUrl = "https://fakestoreapi.com/products";
    private String getAllProductUrl =  "https://fakestoreapi.com/products";

    @Autowired
    public FakeProductService(RestTemplateBuilder restTemplateBuilder, FakeStoreProductClient fakeStoreProductClient){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreProductClient = fakeStoreProductClient;
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
    public GenericProductDto getProductById(Long id) {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.getProductById(id));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.createProduct(genericProductDto));
    }

    public List<GenericProductDto> getAllProducts(){
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreProductClient.getAllProducts();
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            genericProductDtos.add(convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto));
        }
        return genericProductDtos;
    }

    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto){
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.updateProductById(id, genericProductDto));
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.deleteProduct(id));
    }
}
