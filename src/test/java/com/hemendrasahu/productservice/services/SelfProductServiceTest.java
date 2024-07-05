package com.hemendrasahu.productservice.services;

import com.hemendrasahu.productservice.dtos.GenericProductDto;
import com.hemendrasahu.productservice.exceptions.NotFoundException;
import com.hemendrasahu.productservice.models.Category;
import com.hemendrasahu.productservice.models.Product;
import com.hemendrasahu.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SelfProductServiceTest {

    @Autowired
    SelfProductService selfProductService;

    @MockBean
    ProductRepository productRepositoryMock;

    @MockBean
    CategoryService categoryServiceMock;



    @Test
    public void testGetProductByIdThrowsNotFoundExceptionWhenProductWithIdPassedIsNotFound(){
        //arrange
        Optional<Product> mockOptional = Optional.empty();
        when(productRepositoryMock.findById(any(Long.class)))
                .thenReturn(mockOptional);

        //act and assert
        Assertions.assertThrows(NotFoundException.class, () -> selfProductService.getProductById(1L));
    }

    @Test
    public void testGetProductByIdReturnsProductDtoWhenProductWithIdPassedGetsFound() throws NotFoundException {
        //arrange
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setTitle("iPhone");

        Category mockCategory = new Category();
        mockProduct.setCategory(mockCategory);

        Optional<Product> mockOptional = Optional.of(mockProduct);

        when(productRepositoryMock.findById(any(Long.class)))
                .thenReturn(mockOptional);

        //act
        GenericProductDto response = selfProductService.getProductById(1L);

        //assert
        Assertions.assertEquals(1L, response.getId());
        Assertions.assertEquals("iPhone", response.getTitle());
    }

    @Test
    public void testUpdateProductByIdThrowsNotFoundExceptionWhenProductWithIdPassedIsNotFound(){
        //arrange
        Optional<Product> mockOptional = Optional.empty();
        when(productRepositoryMock.findById(any(Long.class)))
                .thenReturn(mockOptional);

        //act and assert
        Assertions.assertThrows(NotFoundException.class, () -> selfProductService.updateProductById(1L, new GenericProductDto()));
    }

    @Test
    public void testUpdateProductByIdReturnsProductDtoWhenProductWithIdPassedGetsFound() throws NotFoundException {
        //arrange
        Product mockProduct = new Product();
        Optional<Product> mockOptional = Optional.of(mockProduct);

        when(productRepositoryMock.findById(any(Long.class)))
                .thenReturn(mockOptional);

        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(1L);
        genericProductDto.setTitle("iPhone");

        //act
        GenericProductDto response = selfProductService.updateProductById(1L, genericProductDto);

        //asset
        Assertions.assertEquals(1L, response.getId());
        Assertions.assertEquals("iPhone", response.getTitle());

    }

    @Test
    public void testCreateProductReturnsProductDtoWhenProductDtoIsPassed(){
        //arrange
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(1L);
        genericProductDto.setTitle("iPhone");

        //act
        GenericProductDto response = selfProductService.createProduct(genericProductDto);

        //assert
        Assertions.assertEquals(1L, response.getId());
        Assertions.assertEquals("iPhone", response.getTitle());
    }

    @Test
    public void testGetAllProductReturnsListOfProductDto(){
        //arrange
        Product mockProduct1 = new Product();
        Category mockCategory = new Category();
        mockProduct1.setCategory(mockCategory);
        mockProduct1.setId(1L);
        mockProduct1.setTitle("iPhone");

        Product mockProduct2 = new Product();
        mockProduct2.setCategory(mockCategory);
        mockProduct2.setId(2L);
        mockProduct2.setTitle("iPhone-2");

        List<Product> mockProductList = new ArrayList<>();
        mockProductList.add(mockProduct1);
        mockProductList.add(mockProduct2);

        when(productRepositoryMock.findAll())
                .thenReturn(mockProductList);

        //act
        List<GenericProductDto> response = selfProductService.getAllProducts();

        //assert
        Assertions.assertEquals(1L, response.get(0).getId()) ;
        Assertions.assertEquals("iPhone", response.get(0).getTitle());

        Assertions.assertEquals(2L, response.get(1).getId());
        Assertions.assertEquals("iPhone-2", response.get(1).getTitle());
    }

    @Test
    public void testDeleteProductReturnsDtoWhenProductWithIdPassedIsFound() throws NotFoundException {
        //arrange
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(1L);
        genericProductDto.setTitle("iPhone");

        SelfProductService selfProductServiceTemp = new SelfProductService(productRepositoryMock, categoryServiceMock);
        SelfProductService selfProductServiceSpy = Mockito.spy(selfProductServiceTemp);

        Mockito.doReturn(genericProductDto).when(selfProductServiceSpy).getProductById(1L);


        //act
        GenericProductDto response = selfProductServiceSpy.deleteProduct(1L);

        //assert
        Assertions.assertEquals(1L, response.getId());
        Assertions.assertEquals("iPhone", response.getTitle());

    }

}
