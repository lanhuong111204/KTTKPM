# Sử dụng node:18-alpine làm base image
FROM node:18-alpine

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép package.json
COPY package*.json ./

# Cài đặt dependencies
RUN npm install

# Sao chép toàn bộ mã nguồn vào container
COPY . .

# Build ứng dụng React
RUN npm run build

# Cài đặt công cụ serve để chạy ứng dụng đã build
RUN npm install -g serve

# Mở cổng 3000
EXPOSE 3000

# Chạy ứng dụng React bằng lệnh serve
CMD ["serve", "-s", "dist", "-l", "3000"]
