# Sử dụng redis:latest làm base image
FROM redis:latest

# Sao chép file cấu hình từ máy host vào bên trong container
COPY redis.conf /usr/local/etc/redis/redis.conf

# Khai báo cổng 6379
EXPOSE 6379

CMD [ "redis-server", "/usr/local/etc/redis/redis.conf" ]
