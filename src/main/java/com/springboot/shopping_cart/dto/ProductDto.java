package com.springboot.shopping_cart.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private String categoryName; // Use only necessary info instead of full Category entity
    private List<ImageDto> images;

}
