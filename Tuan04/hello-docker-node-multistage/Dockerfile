# --- Stage 1: Build ---
# Dùng node:18 để chuẩn bị môi trường và cài đặt dependencies
FROM node:18 AS builder

WORKDIR /app

# Sao chép package.json và cài đặt dependencies
COPY package*.json ./
RUN npm install

# Sao chép toàn bộ mã nguồn
COPY . .


# --- Stage 2: Run ---
# Dùng node:18-alpine để chạy ứng dụng giúp tối ưu dung lượng image
FROM node:18-alpine

WORKDIR /app

# Sao chép toàn bộ thư mục ứng dụng (đã bao gồm node_modules) từ Stage 1 sang
COPY --from=builder /app .

# Mở cổng 3000
EXPOSE 3000

# Lệnh chạy ứng dụng
CMD ["npm", "start"]
