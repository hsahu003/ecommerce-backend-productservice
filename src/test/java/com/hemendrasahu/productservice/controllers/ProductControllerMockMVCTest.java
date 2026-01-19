package com.hemendrasahu.productservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hemendrasahu.productservice.dtos.ProductRequestDto;
import com.hemendrasahu.productservice.dtos.ProductResponseDto;
import com.hemendrasahu.productservice.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMockMVCTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetProductByIdAPI() throws Exception {
        //1. Arrange
        ProductResponseDto expectedResponseDto = new ProductResponseDto();
        expectedResponseDto.setId("1");
        expectedResponseDto.setTitle("Vivobook");
        expectedResponseDto.setCategory("Laptop");

        when(productService.getProductById("1")).thenReturn(expectedResponseDto);

        //2. Act
        ResultActions resultActions = mockMvc.perform(get("/products/1"))
                .andExpect(status().is(200));

        //3. Assert
        String resultString = resultActions.andReturn().getResponse().getContentAsString();
        ProductResponseDto responseDto = objectMapper.readValue(resultString, ProductResponseDto.class);

        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals("1", responseDto.getId());
        Assertions.assertEquals("Vivobook", responseDto.getTitle());

    }

    @Test
    public void testCreateProductAPI() throws Exception {
        //1. Arrange
        ProductResponseDto expectedResponseDto = new ProductResponseDto();
        expectedResponseDto.setId("1");
        expectedResponseDto.setTitle("Vivobook");
        expectedResponseDto.setCategory("Laptop");
        expectedResponseDto.setDescription("Power packed laptop");
        expectedResponseDto.setPrice(10.00);

        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setTitle("Vivobook");
        productRequestDto.setCategory("Laptop");
        productRequestDto.setPrice(10.00);
        when(productService.createProduct(any(ProductRequestDto.class))).thenReturn(expectedResponseDto);

        //2.Act
        ResultActions resultActions = mockMvc.perform(post("/products")
                        .content(objectMapper.writeValueAsString(productRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        String resultString = resultActions.andReturn().getResponse().getContentAsString();
        ProductResponseDto responseDto = objectMapper.readValue(resultString, ProductResponseDto.class);

        //3. Assertion
        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals("1", responseDto.getId());
        Assertions.assertEquals("Vivobook", responseDto.getTitle());
    }

    @Test
    public void testCreateProductAPI_gives400StatusAndGivesErrorInResponse_whenEmptyRequestBodyIsPassed() throws Exception {
        //1. Act
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Bad Request\",\"message\":\"The request body is missing or malformed.\"}"));
    }

    @Test
    public void testCreateProductAPI_gives400StatusAndGivesErrorInResponse_whenInRequestBody_MandatoryFieldsAreMissing() throws Exception {
        //1. Arrange
        ProductRequestDto  productRequestDto = new ProductRequestDto();
        productRequestDto.setImage("/image");
        productRequestDto.setCategory("Laptop");
        productRequestDto.setDescription("Gaming Laptop");

        //2. Act & Assert
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRequestDto)))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"price\":\"Price is required\",\"title\":\"Title cannot be empty\"}"));
    }
}
