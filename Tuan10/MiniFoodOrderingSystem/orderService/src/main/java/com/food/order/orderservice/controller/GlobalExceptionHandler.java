package com.food.order.orderservice.controller;

import com.food.order.orderservice.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(
            MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Object> errorDetails = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("field", fieldName);
            errorMap.put("message", errorMessage);
            errorDetails.add(errorMap);
        });

        ApiResponse<Object> response = ApiResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .success(false)
                .code(400)
                .message("Dữ liệu không hợp lệ")
                .data(null)
                .errors(errorDetails)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(RuntimeException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .success(false)
                .code(404)
                .message(ex.getMessage())
                .data(null)
                .errors(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(io.github.resilience4j.ratelimiter.RequestNotPermitted.class)
    public ResponseEntity<ApiResponse<Object>> handleRateLimitException(io.github.resilience4j.ratelimiter.RequestNotPermitted ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .success(false)
                .code(429)
                .message("Hệ thống đang bận, vui lòng thử lại sau giây lát (Rate Limit Exceeded)")
                .data(null)
                .errors(null)
                .build();
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(response);
    }

    @ExceptionHandler(io.github.resilience4j.circuitbreaker.CallNotPermittedException.class)
    public ResponseEntity<ApiResponse<Object>> handleCircuitBreakerException(io.github.resilience4j.circuitbreaker.CallNotPermittedException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .success(false)
                .code(503)
                .message("Dịch vụ tạm thời ngưng tiếp nhận yêu cầu để bảo trì hệ thống (Circuit Breaker Open)")
                .data(null)
                .errors(null)
                .build();
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }
}
