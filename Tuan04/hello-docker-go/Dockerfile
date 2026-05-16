# --- Bước 1: Build ứng dụng ---
FROM golang:1.21-alpine AS builder

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép file go.mod
COPY go.mod ./

# Sao chép toàn bộ mã nguồn
COPY . .

# Build ứng dụng Go thành một file thực thi (binary) tên là "main"
RUN go build -o main .

# --- Bước 2: Chạy ứng dụng ---
# Sử dụng một image alpine siêu nhẹ để chạy ứng dụng (giúp giảm dung lượng image đáng kể)
FROM alpine:latest

WORKDIR /app

# Sao chép file thực thi "main" từ bước build sang bước này
COPY --from=builder /app/main .

# Mở cổng 8080
EXPOSE 8080

# Chạy file thực thi
CMD ["./main"]
