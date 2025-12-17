package com.hemendrasahu.productservice.services;

import com.hemendrasahu.productservice.dtos.GenericProductDto;
import com.hemendrasahu.productservice.exceptions.NotFoundException;
import com.hemendrasahu.productservice.models.Category;
import com.hemendrasahu.productservice.models.Product;
import com.hemendrasahu.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    public GenericProductDto getProductById(Long id) throws NotFoundException{
        Optional<Product> optional = productRepository.findById(id);
        if(optional.isEmpty()){
            throw new NotFoundException("Product with id " + id + " is not found");
        }
        Product product = optional.get();

        GenericProductDto genericProductDto = objectDtoConverter(product);
        return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product product = objectDtoConverter(genericProductDto);
        productRepository.save(product);
        return genericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();

        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (Product product : products){
            GenericProductDto genericProductDto = objectDtoConverter(product);
            genericProductDtos.add(genericProductDto);
        }

        return genericProductDtos;
    }

    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) throws NotFoundException{
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isEmpty()) {
            throw new NotFoundException("Product with ID " + id + " not found.");
        }

        Product product = objectDtoConverter(genericProductDto);
        product.setId(id);
        productRepository.save(product);
        return genericProductDto;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) throws NotFoundException{
        GenericProductDto genericProductDto = getProductById(id);
        productRepository.deleteById(id);
        return genericProductDto;
    }

    //helper methods
    private GenericProductDto objectDtoConverter(Product product){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(product.getId());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setPrice(product.getPrice());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setCategory(product.getCategory().getName());
        return genericProductDto;
    }

    private Product objectDtoConverter(GenericProductDto genericProductDto){
        Product product = new Product();
        product.setId(genericProductDto.getId());
        product.setTitle(genericProductDto.getTitle());
        product.setDescription(genericProductDto.getDescription());
        product.setCategory(categoryService.getCategoryByName(genericProductDto.getCategory()));
        product.setImage(genericProductDto.getImage());
        product.setPrice(genericProductDto.getPrice());
        return product;
    }
}
