# Sử dụng php:8.2-apache làm base image
FROM php:8.2-apache

# (Tùy chọn) Bật mod_rewrite của Apache, rất hay dùng cho ứng dụng PHP
RUN a2enmod rewrite

# Chuyển thư mục làm việc mặc định sang thư mục phục vụ web của Apache
WORKDIR /var/www/html/

# Khai báo cổng 80
EXPOSE 80

