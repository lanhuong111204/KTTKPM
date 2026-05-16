# Sử dụng python:3.9 làm base image
FROM python:3.9

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép file requirements.txt vào container
COPY requirements.txt ./

# Cài đặt các thư viện yêu cầu
RUN pip install --no-cache-dir -r requirements.txt

# Sao chép toàn bộ mã nguồn vào container
COPY . .

# Expose cổng 5000 để truy cập ứng dụng
EXPOSE 5000

# Lệnh chạy ứng dụng
CMD ["python", "app.py"]
