class DatabaseConnection {
  constructor() {
    this.connectionString = "mongodb://localhost:27017";
    this.isConnected = false;
    console.log("Khởi tạo kết nối DB (Chỉ in ra 1 lần)");
  }

  connect() {
    this.isConnected = true;
    console.log("Đã kết nối!");
  }
}

const instance = new DatabaseConnection();

module.exports = instance;
