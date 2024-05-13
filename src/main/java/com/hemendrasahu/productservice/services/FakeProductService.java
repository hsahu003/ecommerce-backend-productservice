package com.hemendrasahu.productservice.services;

import com.hemendrasahu.productservice.dtos.FakeStoreProductDto;
import com.hemendrasahu.productservice.dtos.GenericProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeProductService")
public class FakeProductService implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private String productUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductUrl = "https://fakestoreapi.com/products";
    @Autowired
    public FakeProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
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
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(
                productUrl,
                FakeStoreProductDto.class,
                id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                createProductUrl,
                genericProductDto,
                FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
    }
}
