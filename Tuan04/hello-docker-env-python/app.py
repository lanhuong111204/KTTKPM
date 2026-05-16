import os

# Đọc biến môi trường APP_ENV, nếu không có thì mặc định là 'Not Set'
app_env = os.environ.get('APP_ENV', 'Not Set')

print("=========================================")
print(f"  Môi trường hiện tại (APP_ENV): {app_env}")
print("=========================================")
