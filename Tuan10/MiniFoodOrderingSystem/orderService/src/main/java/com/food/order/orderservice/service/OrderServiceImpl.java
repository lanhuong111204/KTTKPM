package com.food.order.orderservice.service;

import com.food.order.orderservice.client.FoodClient;
import com.food.order.orderservice.client.UserClient;
import com.food.order.orderservice.dto.OrderRequest;
import com.food.order.orderservice.entity.Order;
import com.food.order.orderservice.entity.OrderItem;
import com.food.order.orderservice.entity.OrderStatus;
import com.food.order.orderservice.repository.OrderRepository;
import feign.FeignException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private FoodClient foodClient;

    @Override
    public Order createOrder(OrderRequest orderRequest) {

        try {
            var userRes = userClient.getUserById(orderRequest.getUserId());
            if(userRes == null || !userRes.isSuccess()) {
                throw new RuntimeException("Dữ liệu User không hợp lệ");
            }
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("User không tồn tại (ID: " + orderRequest.getUserId() + ")");
        } catch (FeignException e) {
            throw new RuntimeException("Lỗi kết nối đến User Service: " + e.getMessage());
        }

        Order order = Order.builder()
                .userId(orderRequest.getUserId())
                .status(OrderStatus.PENDING)
                .orderItems(new ArrayList<>())
                .build();

        double total = 0;

        for (var itemReq : orderRequest.getItems()){
            try {
                var foodRes = foodClient.getFoodById(itemReq.getFoodId());
                if(foodRes != null && foodRes.isSuccess() && foodRes.getData() != null){
                    var food = foodRes.getData();

                    OrderItem orderItem = OrderItem.builder()
                            .foodId(food.getId())
                            .foodName(food.getName())
                            .quantity(itemReq.getQuantity())
                            .priceAtOrder(food.getPrice())
                            .order(order)
                            .build();

                    total += food.getPrice() * itemReq.getQuantity();
                    order.getOrderItems().add(orderItem);
                }
            } catch (FeignException.NotFound e) {
                throw new RuntimeException("Món ăn ID " + itemReq.getFoodId() + " không tồn tại");
            } catch (FeignException e) {
                throw new RuntimeException("Lỗi khi kiểm tra món ăn từ Food Service");
            }
        }

        order.setTotalPrice(total);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Đơn có ID " + id + "Không tồn tại"));

        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng ID: " + id));
    }
}
