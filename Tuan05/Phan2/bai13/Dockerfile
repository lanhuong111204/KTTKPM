# Bước 1: Build ứng dụng React bằng Node.js
FROM node:18-alpine as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Bước 2: Serve ứng dụng bằng Nginx
FROM nginx:alpine
# Copy thư mục build từ bước 1 (mặc định Vite build ra thư mục dist) sang thư mục mặc định của Nginx
COPY --from=build /app/dist /usr/share/nginx/html

# Expose port 80
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
