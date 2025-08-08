package com.springboot.shopping_cart.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductUpdateRequest {

    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Long categoryId;
    private List<Long> imageIds;
}
