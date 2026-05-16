package com.foodordering.payment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * DTO đóng gói response chung cho tất cả API endpoints
 * @param <T> Kiểu dữ liệu của phần data
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse<T> {
    
    /** Trạng thái thành công hay thất bại */
    private Boolean success;

    /** Mã lỗi hoặc mã thành công */
    private Integer code;

    /** Thông báo chi tiết */
    private String message;

    /** Dữ liệu trả về */
    private T data;

    /**
     * Tạo response thành công
     */
    public static <T> ApiResponse<T> ok(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .code(200)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * Tạo response thành công mà không có dữ liệu
     */
    public static <T> ApiResponse<T> ok(String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .code(200)
                .message(message)
                .build();
    }

    /**
     * Tạo response lỗi
     */
    public static <T> ApiResponse<T> error(String message, Integer code) {
        return ApiResponse.<T>builder()
                .success(false)
                .code(code)
                .message(message)
                .build();
    }

    /**
     * Tạo response lỗi với dữ liệu
     */
    public static <T> ApiResponse<T> error(String message, Integer code, T data) {
        return ApiResponse.<T>builder()
                .success(false)
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
}
