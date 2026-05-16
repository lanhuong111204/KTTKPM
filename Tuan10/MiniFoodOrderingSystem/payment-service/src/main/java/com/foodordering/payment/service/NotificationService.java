package com.foodordering.payment.service;

import org.springframework.stereotype.Service;

/**
 * Service gửi thông báo thanh toán
 * Hiện tại chỉ dùng System.out.println để in log
 */
@Service
public class NotificationService {

    /**
     * Gửi thông báo thanh toán thành công
     */
    public void sendNotification(String message) {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║  📢 THÔNG BÁO THANH TOÁN                           ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  " + message);
        System.out.println("╚════════════════════════════════════════════════════╝\n");
    }
}
