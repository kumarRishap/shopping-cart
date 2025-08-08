package com.springboot.shopping_cart.dto;

import com.springboot.shopping_cart.model.Order;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Order> orders;
    private CartDto cart;
}
