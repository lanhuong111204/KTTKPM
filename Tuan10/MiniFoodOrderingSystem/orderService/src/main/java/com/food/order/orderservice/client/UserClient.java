package com.food.order.orderservice.client;

import com.food.order.orderservice.dto.ApiResponse;
import com.food.order.orderservice.dto.UserDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${FEIGN_CLIENTS_USER_SERVICE_URL:http://localhost:8081}")
public interface UserClient {

    @GetMapping("/api/users/{id}")
    @RateLimiter(name = "userServiceRateLimiter")
    @CircuitBreaker(name = "userServiceCircuitBreaker", fallbackMethod = "getUserByIdFallback")
    @Retry(name = "userServiceRetry")
    ApiResponse<UserDTO> getUserById(@PathVariable("id") Long id);

    default ApiResponse<UserDTO> getUserByIdFallback(Long id, Throwable t) {
        return ApiResponse.<UserDTO>builder()
                .success(false)
                .message("User Service tạm thời không khả dụng (Circuit Breaker): " + t.getMessage())
                .build();
    }
}
