package com.hemendrasahu.productservice.thirdpartyclients;

import com.hemendrasahu.productservice.dtos.FakeStoreProductDto;
import com.hemendrasahu.productservice.dtos.GenericProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreProductClient {
    private RestTemplateBuilder restTemplateBuilder;


    private String fakeStoreApiBaseUrl;
    private String fakeStoreProductPath;

    private String productUrl;
    private String productUrlWithParameter;

    @Autowired
    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder,
                                  @Value("${fakestore.baseurl}") String fakeStoreApiBaseUrl,
                                  @Value("${fakestore.path.product}") String fakestoreProductPath){

        this.restTemplateBuilder = restTemplateBuilder;

        this.fakeStoreApiBaseUrl = fakeStoreApiBaseUrl;
        this.fakeStoreProductPath = fakestoreProductPath;

        this.productUrl = this.fakeStoreApiBaseUrl + this.fakeStoreProductPath;
        this.productUrlWithParameter = this.productUrl + "/{id}";
    }
    public FakeStoreProductDto getProductById(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                productUrlWithParameter,
                HttpMethod.GET,
                null,
                FakeStoreProductDto.class,
                id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto[] getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.exchange(
                productUrl,
                HttpMethod.GET,
                null,
                FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();
        return fakeStoreProductDtos;
    }

    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto){
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpEntity<GenericProductDto> httpEntity = new HttpEntity<>(genericProductDto);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                productUrl,
                HttpMethod.POST,
                httpEntity,
                FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return  fakeStoreProductDto;
    }

    public FakeStoreProductDto updateProductById(Long id, GenericProductDto genericProductDto){
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpEntity<GenericProductDto> entity = new HttpEntity<>(genericProductDto);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                productUrlWithParameter,
                HttpMethod.PUT,
                entity,
                FakeStoreProductDto.class,
                id
        );
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto deleteProduct(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                productUrlWithParameter,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto.class,
                id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }
}
