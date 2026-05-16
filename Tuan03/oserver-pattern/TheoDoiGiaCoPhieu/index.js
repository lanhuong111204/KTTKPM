const Stock = require('./Stock');
const Investor = require('./Investor');

// Tạo các cổ phiếu
const appleStock = new Stock("AAPL", 150.0);
const vinamilkStock = new Stock("VNM", 70.0);

// Tạo các nhà đầu tư
const investor1 = new Investor("Nguyễn Văn A");
const investor2 = new Investor("Trần Thị B");
const investor3 = new Investor("Lê Văn C");

// Nhà đầu tư đăng ký theo dõi cổ phiếu
console.log("--- Đăng ký theo dõi cổ phiếu ---");
appleStock.attach(investor1);
appleStock.attach(investor2);

vinamilkStock.attach(investor2);
vinamilkStock.attach(investor3);

// Mô phỏng sự thay đổi giá cổ phiếu
appleStock.setPrice(155.5);
vinamilkStock.setPrice(72.0);

// Hủy đăng ký theo dõi
console.log("\n--- Nhà đầu tư B hủy theo dõi cổ phiếu AAPL ---");
appleStock.detach(investor2);

// Tiếp tục cập nhật giá
appleStock.setPrice(160.0);
