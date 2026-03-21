class Chair {
  sitOn() {
    throw new Error("Method 'sitOn()' phải được implement.");
  }
  hasLegs() {
    throw new Error("Method 'hasLegs()' phải được implement.");
  }
}

class Sofa {
  lieOn() {
    throw new Error("Method 'lieOn()' phải được implement.");
  }
  isComfortable() {
    throw new Error("Method 'isComfortable()' phải được implement.");
  }
}

class CoffeeTable {
  putCoffee() {
    throw new Error("Method 'putCoffee()' phải được implement.");
  }
  getSize() {
    throw new Error("Method 'getSize()' phải được implement.");
  }
}

class ModernChair extends Chair {
  sitOn() {
    console.log("Ngồi trên chiếc Ghế Hiện Đại (Thiết kế tối giản).");
  }
  hasLegs() {
    return false;
  }
}

class ModernSofa extends Sofa {
  lieOn() {
    console.log("Nằm trên chiếc Sofa Hiện Đại (Bọc da cao cấp).");
  }
  isComfortable() {
    return true;
  }
}

class ModernCoffeeTable extends CoffeeTable {
  putCoffee() {
    console.log("Đặt ly cafe lên Bàn Trà Hiện Đại (Mặt kính).");
  }
  getSize() {
    return "Nhỏ gọn";
  }
}

class VictorianChair extends Chair {
  sitOn() {
    console.log("Ngồi trên chiếc Ghế Cổ Điển (Chạm khắc hoa văn).");
  }
  hasLegs() {
    return true;
  }
}

class VictorianSofa extends Sofa {
  lieOn() {
    console.log("Nằm trên chiếc Sofa Cổ Điển (Bọc nhung).");
  }
  isComfortable() {
    return true;
  }
}

class VictorianCoffeeTable extends CoffeeTable {
  putCoffee() {
    console.log("Đặt ly cafe trà chiều lên Bàn Trà Cổ Điển (Gỗ lim).");
  }
  getSize() {
    return "Cồng kềnh, bề thế";
  }
}

class FurnitureFactory {
  createChair() {
    throw new Error("Method 'createChair()' phải được implement.");
  }
  createSofa() {
    throw new Error("Method 'createSofa()' phải được implement.");
  }
  createCoffeeTable() {
    throw new Error("Method 'createCoffeeTable()' phải được implement.");
  }
}

class ModernFurnitureFactory extends FurnitureFactory {
  createChair() {
    return new ModernChair();
  }
  createSofa() {
    return new ModernSofa();
  }
  createCoffeeTable() {
    return new ModernCoffeeTable();
  }
}

class VictorianFurnitureFactory extends FurnitureFactory {
  createChair() {
    return new VictorianChair();
  }
  createSofa() {
    return new VictorianSofa();
  }
  createCoffeeTable() {
    return new VictorianCoffeeTable();
  }
}

class InteriorDesigner {
  constructor(factory) {
    this.chair = factory.createChair();
    this.sofa = factory.createSofa();
    this.table = factory.createCoffeeTable();
  }

  decorateRoom() {
    console.log("--- Bắt đầu trang trí phòng ---");
    this.chair.sitOn();
    this.sofa.lieOn();
    this.table.putCoffee();
    console.log("Kích thước bàn: " + this.table.getSize());
    console.log("-------------------------------\n");
  }
}

function main() {
  console.log(">>> KHÁCH HÀNG A: CHỌN PHONG CÁCH HIỆN ĐẠI <<<");
  const modernFactory = new ModernFurnitureFactory();
  const designer1 = new InteriorDesigner(modernFactory);
  designer1.decorateRoom();

  console.log(">>> KHÁCH HÀNG B: CHỌN PHONG CÁCH CỔ ĐIỂN <<<");
  const victorianFactory = new VictorianFurnitureFactory();
  const designer2 = new InteriorDesigner(victorianFactory);
  designer2.decorateRoom();
}

main();
