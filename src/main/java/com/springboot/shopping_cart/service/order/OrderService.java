package com.springboot.shopping_cart.service.order;

import com.springboot.shopping_cart.dto.OrderDto;
import com.springboot.shopping_cart.enums.OrderStatus;
import com.springboot.shopping_cart.exception.ResourceNotFoundException;
import com.springboot.shopping_cart.model.Cart;
import com.springboot.shopping_cart.model.Order;
import com.springboot.shopping_cart.model.OrderItem;
import com.springboot.shopping_cart.model.Product;
import com.springboot.shopping_cart.repository.OrderRepository;
import com.springboot.shopping_cart.repository.ProductRepository;
import com.springboot.shopping_cart.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final CartService cartService;

    private final ModelMapper modelMapper;

    @Override
    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        Order order = createOrder(cart);
        List<OrderItem> orderItemList = createOrderItems(order, cart);
        order.setOrderItems(new HashSet<>(orderItemList));
        order.setTotalAmount(calculateTotalAmount(orderItemList));
        Order savedOrder = orderRepository.save(order);

        cartService.clearCart(cart.getId());
        return savedOrder;
    }

    @Override
    public OrderDto getOrderId(Long orderId) {
        return orderRepository.findById(orderId).map(this::convertToDto).orElseThrow(() -> new ResourceNotFoundException("Order not found!"));
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList) {
        return orderItemList.stream().map(item -> item.getTotalPrice().multiply(new BigDecimal(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<OrderItem> createOrderItems(Order order, Cart cart) {

        return cart.getItems().stream().map(item -> {
            Product product = item.getProduct();
            product.setInventory(product.getInventory() - item.getQuantity());
            productRepository.save(product);
            return new OrderItem(order, product, item.getQuantity(), item.getUnitPrice());
        }).toList();
    }

    private Order createOrder(Cart cart) {

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());

        return order;
    }

    @Override
    public List<OrderDto> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId).stream().map(this::convertToDto).toList();
    }

    private OrderDto convertToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }
}
