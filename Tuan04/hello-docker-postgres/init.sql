-- Tạo một database tùy chỉnh
CREATE DATABASE my_custom_db;

-- Kết nối vào database vừa tạo
\c my_custom_db;

-- Tạo một bảng người dùng mẫu
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Thêm một vài dữ liệu mẫu
INSERT INTO users (username, email) VALUES 
('admin', 'admin@example.com'),
('docker_fan', 'docker@example.com');
