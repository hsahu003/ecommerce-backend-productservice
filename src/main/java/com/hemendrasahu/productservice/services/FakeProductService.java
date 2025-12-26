package com.hemendrasahu.productservice.services;

import com.hemendrasahu.productservice.dtos.ProductRequestDto;
import com.hemendrasahu.productservice.dtos.ProductResponseDto;
import com.hemendrasahu.productservice.exceptions.NotFoundException;
import com.hemendrasahu.productservice.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;
import com.hemendrasahu.productservice.dtos.GenericProductDto;
import com.hemendrasahu.productservice.thirdpartyclients.fakestore.FakeStoreProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Primary
@Service("fakeProductService")
public class FakeProductService implements ProductService{

    private FakeStoreProductClient fakeStoreProductClient;

    @Autowired
    public FakeProductService(FakeStoreProductClient fakeStoreProductClient){
        this.fakeStoreProductClient = fakeStoreProductClient;
    }

    private FakeStoreProductDto convertProductRequestDtoToFakeStoreDto (ProductRequestDto productCreateRequestDto) {
        if (productCreateRequestDto == null) return null;
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(productCreateRequestDto.getTitle());
        fakeStoreProductDto.setDescription(productCreateRequestDto.getDescription());
        fakeStoreProductDto.setImage(productCreateRequestDto.getImage());
        fakeStoreProductDto.setCategory(productCreateRequestDto.getCategory());
        fakeStoreProductDto.setPrice(productCreateRequestDto.getPrice());
        return fakeStoreProductDto;
    }

    private ProductResponseDto convertFakeStoreDtoToProductResponseDto(FakeStoreProductDto fakeStoreProductDto) {
        if (fakeStoreProductDto == null) return null;
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(String.valueOf(fakeStoreProductDto.getId()));
        productResponseDto.setTitle(fakeStoreProductDto.getTitle());
        productResponseDto.setDescription(fakeStoreProductDto.getDescription());
        productResponseDto.setImage(fakeStoreProductDto.getImage());
        productResponseDto.setCategory(fakeStoreProductDto.getCategory());
        productResponseDto.setPrice(fakeStoreProductDto.getPrice());
        return productResponseDto;
    }

    @Override
    public ProductResponseDto getProductById(String id) throws NotFoundException {
        return convertFakeStoreDtoToProductResponseDto(fakeStoreProductClient.getProductById(Long.parseLong(id)));
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        FakeStoreProductDto fakeStoreProductDto = convertProductRequestDtoToFakeStoreDto(productRequestDto);
        return convertFakeStoreDtoToProductResponseDto  (fakeStoreProductClient.createProduct(fakeStoreProductDto));
    }

    @Override
    public List<ProductResponseDto> getAllProducts(){
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreProductClient.getAllProducts();
        List<ProductResponseDto> productResponseDto = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            productResponseDto.add(convertFakeStoreDtoToProductResponseDto(fakeStoreProductDto));
        }
        return productResponseDto;
    }

    @Override
    public ProductResponseDto updateProductById(String id, ProductRequestDto productRequestDto) throws NotFoundException{
        FakeStoreProductDto fakeStoreProductDto = convertProductRequestDtoToFakeStoreDto(productRequestDto);
        return convertFakeStoreDtoToProductResponseDto(fakeStoreProductClient.updateProductById(Long.parseLong(id), fakeStoreProductDto));
    }

    @Override
    public ProductResponseDto deleteProduct(String id) {
        return convertFakeStoreDtoToProductResponseDto(fakeStoreProductClient.deleteProduct(Long.parseLong(id)));
    }
}
