-- ============================================
-- SQL Script: Khởi tạo Payment Service Database
-- ============================================

-- Tạo database
CREATE DATABASE IF NOT EXISTS payment_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Tạo user và cấp quyền
CREATE USER IF NOT EXISTS 'payment_user'@'localhost' IDENTIFIED BY 'payment_pass';
GRANT ALL PRIVILEGES ON payment_db.* TO 'payment_user'@'localhost';

-- Để chạy trong Docker, cần thêm
CREATE USER IF NOT EXISTS 'payment_user'@'%' IDENTIFIED BY 'payment_pass';
GRANT ALL PRIVILEGES ON payment_db.* TO 'payment_user'@'%';

FLUSH PRIVILEGES;

-- Sử dụng database
USE payment_db;

-- Tạo bảng payments
CREATE TABLE IF NOT EXISTS payments (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  order_id BIGINT NOT NULL,
  amount DOUBLE NOT NULL,
  method VARCHAR(50) NOT NULL,
  status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
  payment_time DATETIME NOT NULL,
  notes TEXT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  
  INDEX idx_order_id (order_id),
  INDEX idx_status (status),
  INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insert dữ liệu test
INSERT INTO payments (order_id, amount, method, status, payment_time, notes) VALUES
(1, 150000, 'BANKING', 'SUCCESS', NOW(), 'Test payment 1'),
(2, 200000, 'COD', 'PENDING', NOW(), 'Test payment 2');

SELECT * FROM payments;
