package com.hemendrasahu.productservice.services;

import com.hemendrasahu.productservice.dtos.GenericProductDto;
import com.hemendrasahu.productservice.dtos.ProductRequestDto;
import com.hemendrasahu.productservice.dtos.ProductResponseDto;
import com.hemendrasahu.productservice.exceptions.NotFoundException;
import com.hemendrasahu.productservice.models.Product;
import com.hemendrasahu.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryService categoryService;

    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryService categoryService){
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public ProductResponseDto getProductById(String id) throws NotFoundException{
        Optional<Product> optional = productRepository.findById(UUID.fromString(id));
        if(optional.isEmpty()){
            throw new NotFoundException("Product with id " + id + " is not found");
        }
        Product product = optional.get();

        return objectDtoConverter(product);
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = objectDtoConverter(productRequestDto);
        Product savedProduct = productRepository.save(product);
        return objectDtoConverter(savedProduct);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for (Product product : products){
            ProductResponseDto productResponseDto = objectDtoConverter(product);
            productResponseDtos.add(productResponseDto);
        }

        return productResponseDtos;
    }

    public ProductResponseDto updateProductById(String id, ProductRequestDto productRequestDto) throws NotFoundException{
        Optional<Product> existingProduct = productRepository.findById(UUID.fromString(id));

        if (existingProduct.isEmpty()) {
            throw new NotFoundException("Product with ID " + id + " not found.");
        }

        Product product = objectDtoConverter(productRequestDto);
        Product savedProduct = productRepository.save(product);
        return objectDtoConverter(savedProduct);
    }

    @Override
    public ProductResponseDto deleteProduct(String id) throws NotFoundException{
        ProductResponseDto productResponseDto = getProductById(id);
        productRepository.deleteById(UUID.fromString(id));
        return productResponseDto;
    }

    //helper methods
    private ProductResponseDto objectDtoConverter(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(String.valueOf(product.getId()));
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setImage(product.getImage());
        productResponseDto.setCategory(product.getCategory().getName());
        productResponseDto.setPrice(product.getPrice());
        return productResponseDto;
    }

    private Product objectDtoConverter(ProductRequestDto productRequestDto){
        Product product = new Product();
        product.setTitle(productRequestDto.getTitle());
        product.setDescription(productRequestDto.getDescription());
        product.setCategory(categoryService.getCategoryByName(productRequestDto.getCategory()));
        product.setImage(productRequestDto.getImage());
        product.setPrice(productRequestDto.getPrice());
        return product;
    }


}
