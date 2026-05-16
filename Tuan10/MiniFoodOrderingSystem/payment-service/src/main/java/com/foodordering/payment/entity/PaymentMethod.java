package com.foodordering.payment.entity;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum định nghĩa các phương thức thanh toán
 */
public enum PaymentMethod {
    /** Thanh toán khi nhận (Cash On Delivery) */
    COD("Thanh toán khi nhận"),
    /** Thanh toán qua ngân hàng */
    BANKING("Thanh toán qua ngân hàng");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @JsonValue
    public String getCode() {
        return name();
    }
}
