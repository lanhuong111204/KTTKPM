
class Transport {
    deliver() {
        throw new Error("Method 'deliver()' phải được implement ở lớp con.");
    }
}

class Truck extends Transport {
    deliver() {
        console.log("Giao hàng bằng đường bộ trong một chiếc hộp trên Xe Tải.");
    }
}

class Ship extends Transport {
    deliver() {
        console.log("Giao hàng bằng đường thuỷ trong một container trên Tàu Thủy.");
    }
}

class Logistics {
    constructor() {
        if (new.target === Logistics) {
            throw new TypeError("Không thể khởi tạo trực tiếp Abstract Class Logistics.");
        }
    }
    createTransport() {
        throw new Error("Method 'createTransport()' phải được implement ở lớp con.");
    }
    planDelivery() {
        console.log("Logistics: Bắt đầu lên kế hoạch vận chuyển...");
        
        const transport = this.createTransport();
    
        transport.deliver();
    }
}

class RoadLogistics extends Logistics {
    createTransport() {
        console.log("   -> [RoadLogistics Factory] Tạo ra một chiếc Xe Tải (Truck).");
        return new Truck();
    }
}

class SeaLogistics extends Logistics {
    createTransport() {
        console.log("   -> [SeaLogistics Factory] Tạo ra một chiếc Tàu Thủy (Ship).");
        return new Ship();
    }
}


function main() {
    console.log("--- TRƯỜNG HỢP 1: KHÁCH HÀNG CHỌN ĐƯỜNG BỘ ---");
    const roadLogistics = new RoadLogistics();
    roadLogistics.planDelivery(); 
    console.log("\n--- TRƯỜNG HỢP 2: KHÁCH HÀNG CHỌN ĐƯỜNG BIỂN ---");
    const seaLogistics = new SeaLogistics();
    seaLogistics.planDelivery(); 
}

main();