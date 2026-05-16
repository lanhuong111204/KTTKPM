package com.foodordering.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Điểm bắt đầu của ứng dụng Payment Service
 * Kích hoạt OpenFeign để gọi các service khác
 */
@SpringBootApplication
@EnableFeignClients
public class PaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
