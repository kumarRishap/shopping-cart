package com.springboot.shopping_cart.service.order;

import com.springboot.shopping_cart.dto.OrderDto;
import com.springboot.shopping_cart.model.Order;

import java.util.List;

public interface IOrderService {

    Order placeOrder(Long userId);

    OrderDto getOrderId(Long orderId);

    List<OrderDto> getUserOrders(Long userId);
}
