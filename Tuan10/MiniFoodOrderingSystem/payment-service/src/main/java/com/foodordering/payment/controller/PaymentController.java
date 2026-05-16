package com.foodordering.payment.controller;

import com.foodordering.payment.dto.ApiResponse;
import com.foodordering.payment.dto.PaymentRequest;
import com.foodordering.payment.dto.PaymentResponse;
import com.foodordering.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * REST Controller xử lý thanh toán
 * Endpoint: POST /api/payments
 */
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Xử lý thanh toán đơn hàng
     * POST /api/payments
     * 
     * Request: {
     *   "order_id": 1,
     *   "payment_method": "BANKING",
     *   "amount": 150000.0,
     *   "notes": "Thanh toán qua ngân hàng"
     * }
     * 
     * Response: {
     *   "success": true,
     *   "code": 200,
     *   "message": "Thanh toán thành công",
     *   "data": {
     *     "id": 1,
     *     "order_id": 1,
     *     "amount": 150000.0,
     *     "payment_method": "BANKING",
     *     "status": "SUCCESS",
     *     "payment_time": "2026-04-23T20:42:00",
     *     "created_at": "2026-04-23T20:42:00",
     *     "updated_at": "2026-04-23T20:42:00"
     *   }
     * }
     * 
     * @param paymentRequest Yêu cầu thanh toán
     * @return ApiResponse<PaymentResponse> chứa thông tin thanh toán đã lưu
     */
    @PostMapping
    public ResponseEntity<ApiResponse<PaymentResponse>> processPayment(
            @Valid @RequestBody PaymentRequest paymentRequest) {
        try {
            log.info("=== XỬ LÝ THANH TOÁN ===");
            log.info("Đơn hàng: {}", paymentRequest.getOrderId());
            log.info("Phương thức: {}", paymentRequest.getPaymentMethod());
            log.info("Số tiền: {}", paymentRequest.getAmount());
            
            PaymentResponse response = paymentService.processPayment(paymentRequest);
            
            return ResponseEntity.ok(
                    ApiResponse.ok("Thanh toán thành công", response)
            );
        } catch (Exception e) {
            log.error("Lỗi xử lý thanh toán: {}", e.getMessage(), e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Lỗi: " + e.getMessage(), 500));
        }
    }
}
