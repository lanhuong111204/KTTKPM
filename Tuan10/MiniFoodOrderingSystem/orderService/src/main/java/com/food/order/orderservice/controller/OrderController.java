package com.food.order.orderservice.controller;

import com.food.order.orderservice.dto.ApiResponse;
import com.food.order.orderservice.dto.OrderRequest;
import com.food.order.orderservice.entity.Order;
import com.food.order.orderservice.entity.OrderStatus;
import com.food.order.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        Order newOrder = orderService.createOrder(orderRequest);

        ApiResponse<Order> response = ApiResponse.<Order>builder()
                .timestamp(LocalDateTime.now().toString())
                .success(true)
                .code(201)
                .message("Tạo đơn hàng thành công")
                .data(newOrder)
                .build();

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Order>>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();

        ApiResponse<List<Order>> response = ApiResponse.<List<Order>>builder()
                .timestamp(LocalDateTime.now().toString())
                .success(true)
                .code(200)
                .message("Lấy danh sách đơn hàng thành công")
                .data(orders)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Order>>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);

        ApiResponse<List<Order>> response = ApiResponse.<List<Order>>builder()
                .timestamp(LocalDateTime.now().toString())
                .success(true)
                .code(200)
                .message("Lấy lịch sử đơn hàng của user " + userId + "Thành công")
                .data(orders)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Order>> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        Order updateOrder = orderService.updateOrderStatus(id, status);

        ApiResponse<Order> response = ApiResponse.<Order>builder()
                .timestamp(LocalDateTime.now().toString())
                .success(true)
                .code(200)
                .message("Cập nhật trạng thái đơn hàng thành công")
                .data(updateOrder)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);

        ApiResponse<Order> response = ApiResponse.<Order>builder()
                .timestamp(LocalDateTime.now().toString())
                .success(true)
                .code(200)
                .message("Lấy thông tin đơn hàng thành công")
                .data(order)
                .build();

        return ResponseEntity.ok(response);
    }



}
