package com.foodordering.payment.service;

import com.foodordering.payment.client.OrderServiceClient;
import com.foodordering.payment.dto.ApiResponse;
import com.foodordering.payment.dto.PaymentRequest;
import com.foodordering.payment.dto.PaymentResponse;
import com.foodordering.payment.entity.Payment;
import com.foodordering.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Service xử lý thanh toán
 * - Nhận yêu cầu thanh toán
 * - Lưu vào database
 * - Cập nhật trạng thái Order Service
 * - Gửi thông báo
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderServiceClient orderServiceClient;
    private final NotificationService notificationService;

    /**
     * Xử lý yêu cầu thanh toán
     * 1. Lưu log thanh toán
     * 2. Gọi Order Service cập nhật status -> PAID
     * 3. Gọi NotificationService để báo tin
     */
    public PaymentResponse processPayment(PaymentRequest paymentRequest) throws Exception {
        log.info("📝 Bắt đầu xử lý thanh toán cho đơn hàng [{}]", paymentRequest.getOrderId());

        ApiResponse<Map<String, Object>> orderRes;
        try {
            orderRes = orderServiceClient.getOrderById(paymentRequest.getOrderId());
        } catch (Exception e) {
            log.error("Chi tiết lỗi khi gọi Order Service: ", e);
            throw new RuntimeException("Không tìm thấy đơn hàng hoặc Order Service đang lỗi.");
        }

        if (orderRes == null || !orderRes.getSuccess() || orderRes.getData() == null) {
            throw new RuntimeException("Đơn hàng không tồn tại.");
        }

        Map<String, Object> orderData = orderRes.getData();
        String currentStatus = (String) orderData.get("status");
        Double totalPrice = Double.valueOf(orderData.get("totalPrice").toString());

        if (!"PENDING".equals(currentStatus)) {
            throw new RuntimeException("Đơn hàng này không ở trạng thái chờ thanh toán (Hiện tại: " + currentStatus + ")");
        }

        if (!totalPrice.equals(paymentRequest.getAmount())) {
            throw new RuntimeException("Số tiền thanh toán không khớp! Cần thanh toán: " + totalPrice + ", nhưng nhận được: " + paymentRequest.getAmount());
        }

        Payment payment = Payment.builder()
                .orderId(paymentRequest.getOrderId())
                .amount(paymentRequest.getAmount())
                .method(paymentRequest.getPaymentMethod())
                .status("SUCCESS")
                .paymentTime(LocalDateTime.now())
                .notes(paymentRequest.getNotes())
                .build();

        Payment savedPayment = paymentRepository.save(payment);
        log.info("✓ Đã lưu log thanh toán (ID: {}) vào database", savedPayment.getId());

        orderServiceClient.updateOrderStatus(paymentRequest.getOrderId(), "PAID");
        log.info("✓ Đã cập nhật Order Service - trạng thái thành PAID");

        String message = String.format(
                "Đơn hàng [%d] đã thanh toán thành công: %.0f VNĐ via %s",
                paymentRequest.getOrderId(),
                paymentRequest.getAmount(),
                paymentRequest.getPaymentMethod().getDescription()
        );
        notificationService.sendNotification(message);

        log.info("✅ Thanh toán hoàn tất!");
        return convertToResponse(savedPayment);
    }

    /**
     * Convert Payment entity sang DTO
     */
    private PaymentResponse convertToResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getMethod())
                .status(payment.getStatus())
                .paymentTime(payment.getPaymentTime())
                .notes(payment.getNotes())
                .createdAt(payment.getCreatedAt())
                .updatedAt(payment.getUpdatedAt())
                .build();
    }
}
