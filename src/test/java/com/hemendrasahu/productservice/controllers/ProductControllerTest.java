package com.hemendrasahu.productservice.controllers;

import com.hemendrasahu.productservice.dtos.ProductRequestDto;
import com.hemendrasahu.productservice.dtos.ProductResponseDto;
import com.hemendrasahu.productservice.exceptions.NotFoundException;
import com.hemendrasahu.productservice.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    ProductController productController;

    @MockBean
    ProductService productServiceMock;

    /*----------------getProductById()------------------*/
    @Test
    public void testGetProductByIdReturnsCorrectProduct() throws NotFoundException {
        //Arrange
        ProductResponseDto toBeReturned = new ProductResponseDto();
        toBeReturned.setId("1");
        toBeReturned.setTitle("Test Product");

        when(productServiceMock.getProductById(any()))
                .thenReturn(toBeReturned);

        //Act
        ProductResponseDto actualResponseDto = productController.getProductById("1");

        //Assert
        verify(productServiceMock).getProductById("1");
        Assertions.assertNotNull(actualResponseDto);
        Assertions.assertEquals("1", actualResponseDto.getId());
        Assertions.assertEquals("Test Product", actualResponseDto.getTitle());
    }

    @Test
    public void testGetProductByIdThrowsNotFoundException() throws NotFoundException {
        //Arrange
        when(productServiceMock.getProductById(any()))
                .thenThrow(new NotFoundException("Product not found"));

        //Act & Assert
        Assertions.assertThrows(NotFoundException.class, () -> productController.getProductById("1"));
    }

    /*----------------createProduct()------------------*/

    @Test
    public void testCreateProductReturnsCreatedProduct(){
        //1. Arrange
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setTitle("Kindle");
        productRequestDto.setDescription("e-book reader");

        ProductResponseDto expectedResponseDto = new ProductResponseDto();
        expectedResponseDto.setId("1");
        expectedResponseDto.setTitle("Kindle");
        expectedResponseDto.setDescription("e-book reader");

        //Define captor
        ArgumentCaptor<ProductRequestDto> captor = ArgumentCaptor.forClass(ProductRequestDto.class);

        //Stub the service
        when(productServiceMock.createProduct(any(ProductRequestDto.class))).thenReturn(expectedResponseDto);

        //2. Act
        ProductResponseDto actualResponseDto = productController.createProduct(productRequestDto);

        // 3. Assert & Verify
        verify(productServiceMock).createProduct(captor.capture());

        // Check what the Service actually received
        ProductRequestDto capturedRequest = captor.getValue();
        Assertions.assertEquals("Kindle", capturedRequest.getTitle());
        Assertions.assertEquals("e-book reader", capturedRequest.getDescription());

        // Check what the Controller returned
        Assertions.assertNotNull(actualResponseDto, "Returned product should not be null");
        Assertions.assertEquals("1", actualResponseDto.getId());
        Assertions.assertEquals("Kindle", actualResponseDto.getTitle());
        Assertions.assertEquals("e-book reader", actualResponseDto.getDescription());

    }




}
