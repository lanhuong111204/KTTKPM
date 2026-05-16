# Sử dụng node:18 làm base image
FROM node:18

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép file package.json vào trong container
COPY package*.json ./

# Cài đặt các dependencies
RUN npm install

# Sao chép toàn bộ mã nguồn vào trong container
COPY . .

# Mở cổng 3000
EXPOSE 3000

# Khởi chạy ứng dụng
CMD ["npm", "start"]
