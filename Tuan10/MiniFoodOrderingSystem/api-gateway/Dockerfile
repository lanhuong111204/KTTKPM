# Stage 1: Build file JAR bằng Maven
FROM maven:3.9.5-eclipse-temurin-17 AS builder
WORKDIR /app
# Copy file pom và source code vào container
COPY pom.xml .
COPY src ./src
# Build ra file .jar (Bỏ qua chạy test để build nhanh hơn)
RUN mvn clean package -DskipTests

# Stage 2: Chạy ứng dụng
FROM eclipse-temurin:17-jre
WORKDIR /app
# Lấy file .jar từ Stage 1 sang Stage 2
COPY --from=builder /app/target/*.jar app.jar
# Lệnh khởi chạy Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]