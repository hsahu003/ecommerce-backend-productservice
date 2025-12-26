package com.hemendrasahu.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
