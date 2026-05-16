package com.food.order.orderservice.service;

import com.food.order.orderservice.dto.OrderRequest;
import com.food.order.orderservice.entity.Order;
import com.food.order.orderservice.entity.OrderStatus;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderRequest orderRequest);
    List<Order> getAllOrders();
    List<Order> getOrdersByUserId(Long userId);
    Order updateOrderStatus(Long id, OrderStatus status);
    Order getOrderById(Long id);
}
