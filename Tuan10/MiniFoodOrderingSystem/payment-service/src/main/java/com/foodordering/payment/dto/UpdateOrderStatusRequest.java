package com.foodordering.payment.dto;

import lombok.*;

/**
 * DTO gửi yêu cầu cập nhật trạng thái đơn hàng tới Order Service
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateOrderStatusRequest {
    
    /** Trạng thái mới của đơn hàng */
    private String status;
}
