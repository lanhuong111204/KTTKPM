# Sử dụng postgres:15 làm base image theo yêu cầu
FROM postgres:15

# Đặt mật khẩu mặc định 
ENV POSTGRES_PASSWORD=mysecretpassword

# Sao chép file init.sql vào thư mục đặc biệt của PostgreSQL
# Tất cả các file .sql, .sql.gz, hoặc .sh đặt trong /docker-entrypoint-initdb.d/
# sẽ TỰ ĐỘNG ĐƯỢC CHẠY khi container khởi động lần đầu tiên
COPY init.sql /docker-entrypoint-initdb.d/
