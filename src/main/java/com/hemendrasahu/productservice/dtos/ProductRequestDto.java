package com.hemendrasahu.productservice.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String id;
    @NotBlank(message = "Title cannot be empty")
    private String title;
    private String description;
    private String image;
    private String category;
    @NotBlank(message = "Price cannot be empty")
    private double price;
}
