package com.food.order.userservice.service;

import com.food.order.userservice.DTOs.ApiResponse;
import com.food.order.userservice.DTOs.ValidationError;
import com.food.order.userservice.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex){
        List<ValidationError> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> new ValidationError(e.getField(), e.getDefaultMessage()))
                .collect(Collectors.toList());

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .timestamp(ZonedDateTime.now().toString())
                .success(false).code(400).message("Dữ liệu không hợp lệ")
                .errors(errors).build();

        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusiness(BusinessException ex) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .timestamp(ZonedDateTime.now().toString())
                .success(false).code(422).message(ex.getMessage()).build();
        return ResponseEntity.status(422).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGlobalException(Exception ex) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .timestamp(ZonedDateTime.now().toString())
                .success(false).code(500)
                .message("Lỗi hệ thống: " + ex.getMessage())
                .build();
        return ResponseEntity.status(500).body(response);
    }
}