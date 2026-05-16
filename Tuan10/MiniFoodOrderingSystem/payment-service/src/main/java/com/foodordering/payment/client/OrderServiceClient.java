package com.foodordering.payment.client;

import com.foodordering.payment.dto.ApiResponse;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * OpenFeign Client để gọi Order Service
 * Gọi endpoint cập nhật trạng thái đơn hàng
 */
@FeignClient(
    name = "order-service",
    url = "${FEIGN_CLIENTS_ORDER_SERVICE_URL:http://localhost:8083}"
)
public interface OrderServiceClient {
    
    @GetMapping("/api/orders/{id}")
    ApiResponse<Map<String, Object>> getOrderById(@PathVariable("id") Long orderId);
    
    /**
     * Cập nhật trạng thái đơn hàng
     * 
     * @param orderId ID đơn hàng
     * @param request Request chứa trạng thái mới
     * @return Response từ Order Service
     */
    @PutMapping("/api/orders/{id}/status")
    Object updateOrderStatus(
            @PathVariable("id") Long orderId,
            @RequestParam("status") String status
    );
}
