package com.foodordering.payment.dto;

import com.foodordering.payment.entity.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * DTO nhận yêu cầu thanh toán từ client
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentRequest {
    
    /** ID đơn hàng cần thanh toán */
    @NotNull(message = "ID đơn hàng không được để trống")
    @JsonProperty("order_id")
    private Long orderId;

    /** Phương thức thanh toán */
    @NotNull(message = "Phương thức thanh toán không được để trống")
    @JsonProperty("payment_method")
    private PaymentMethod paymentMethod;

    /** Số tiền thanh toán */
    @NotNull(message = "Số tiền không được để trống")
    @DecimalMin(value = "0.1", message = "Số tiền phải lớn hơn 0")
    private Double amount;

    /** Ghi chú thêm */
    private String notes;
}
