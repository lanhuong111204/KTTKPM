package com.foodordering.payment.repository;

import com.foodordering.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository cho Entity Payment
 * Cung cấp các method CRUD và custom queries
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    /**
     * Tìm tất cả thanh toán theo ID đơn hàng
     * @param orderId ID đơn hàng
     * @return Danh sách thanh toán
     */
    List<Payment> findByOrderId(Long orderId);

    /**
     * Tìm thanh toán gần nhất theo ID đơn hàng
     * @param orderId ID đơn hàng
     * @return Optional chứa thanh toán nếu tồn tại
     */
    Optional<Payment> findFirstByOrderIdOrderByCreatedAtDesc(Long orderId);

    /**
     * Tìm tất cả thanh toán theo trạng thái
     * @param status Trạng thái thanh toán
     * @return Danh sách thanh toán
     */
    List<Payment> findByStatus(String status);

    /**
     * Đếm số thanh toán thành công
     * @return Số lượng thanh toán thành công
     */
    long countByStatus(String status);
}
