# Sử dụng python:3.9 siêu nhẹ làm base image
FROM python:3.9-slim

# Thiết lập thư mục làm việc
WORKDIR /app

# Đặt biến môi trường
ENV APP_ENV=development

# Sao chép mã nguồn vào container
COPY app.py .

# Chạy file Python
CMD ["python", "app.py"]
