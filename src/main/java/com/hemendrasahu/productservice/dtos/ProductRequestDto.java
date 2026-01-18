package com.hemendrasahu.productservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @Positive(message = "Price must be greater than zero")
    @NotNull(message = "Price is required")
    private Double price;
}
