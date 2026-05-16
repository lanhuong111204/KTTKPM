package com.food.order.orderservice.client;

import com.food.order.orderservice.dto.ApiResponse;
import com.food.order.orderservice.dto.FoodDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "food-service", url = "${FEIGN_CLIENTS_FOOD_SERVICE_URL:http://localhost:8082}")
public interface FoodClient {

    @GetMapping("/api/foods/{id}")
    @RateLimiter(name = "foodServiceRateLimiter")
    @CircuitBreaker(name = "foodServiceCircuitBreaker", fallbackMethod = "getFoodByIdFallback")
    @Retry(name = "foodServiceRetry")
    ApiResponse<FoodDTO>  getFoodById(@PathVariable("id") Long id);

    default ApiResponse<FoodDTO> getFoodByIdFallback(Long id, Throwable t) {
        return ApiResponse.<FoodDTO>builder()
                .success(false)
                .message("Food Service tạm thời không khả dụng (Circuit Breaker): " + t.getMessage())
                .build();
    }
}
