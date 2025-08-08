package com.springboot.shopping_cart.controller;

import com.springboot.shopping_cart.dto.OrderDto;
import com.springboot.shopping_cart.model.Order;
import com.springboot.shopping_cart.response.ApiResponse;
import com.springboot.shopping_cart.service.order.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    // Place an order for a user
    @PostMapping
    public ResponseEntity<ApiResponse> createOrder(@RequestParam Long userId) {
        Order order = orderService.placeOrder(userId);
        return ResponseEntity.ok(new ApiResponse("Order placed successfully!", order));
    }

    // Get a single order for a user
    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long orderId) {
        OrderDto order = orderService.getOrderId(orderId);
        return ResponseEntity.ok(new ApiResponse("Order fetched successfully!", order));
    }

    // Get all orders for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getUserOrders(@PathVariable Long userId) {
        List<OrderDto> orders = orderService.getUserOrders(userId);
        return ResponseEntity.ok(new ApiResponse("User orders fetched successfully!", orders));
    }
}
