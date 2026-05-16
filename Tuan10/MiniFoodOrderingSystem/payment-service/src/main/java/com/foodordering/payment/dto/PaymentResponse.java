package com.foodordering.payment.dto;

import com.foodordering.payment.entity.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;

/**
 * DTO trả về thông tin thanh toán cho client
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentResponse {
    
    /** ID giao dịch thanh toán */
    private Long id;

    /** ID đơn hàng */
    @JsonProperty("order_id")
    private Long orderId;

    /** Số tiền đã thanh toán */
    private Double amount;

    /** Phương thức thanh toán */
    @JsonProperty("payment_method")
    private PaymentMethod paymentMethod;

    /** Trạng thái thanh toán */
    private String status;

    /** Thời gian thanh toán */
    @JsonProperty("payment_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime paymentTime;

    /** Ghi chú */
    private String notes;

    /** Thời gian tạo bản ghi */
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    /** Thời gian cập nhật */
    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}
