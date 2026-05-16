package com.foodordering.payment.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Entity đại diện cho một giao dịch thanh toán
 * Lưu trữ thông tin chi tiết về thanh toán đơn hàng
 */
@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Payment {
    
    /** ID duy nhất của giao dịch thanh toán */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** ID đơn hàng được thanh toán */
    @Column(nullable = false)
    private Long orderId;

    /** Số tiền thanh toán */
    @Column(nullable = false)
    private Double amount;

    /** Phương thức thanh toán (COD hoặc BANKING) */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    /** Trạng thái thanh toán (PENDING, SUCCESS, FAILED) */
    @Column(nullable = false)
    private String status;

    /** Thời gian ghi nhận thanh toán */
    @Column(nullable = false)
    private LocalDateTime paymentTime;

    /** Ghi chú thêm về giao dịch */
    @Column(columnDefinition = "TEXT")
    private String notes;

    /** Thời gian tạo bản ghi */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /** Thời gian cập nhật gần nhất */
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Hook được gọi trước khi save entity
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "PENDING";
        }
    }

    /**
     * Hook được gọi trước khi update entity
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
