const Subject = require('./Subject');

class Stock extends Subject {
    constructor(symbol, price) {
        super();
        this.symbol = symbol;
        this.price = price;
    }

    setPrice(newPrice) {
        console.log(`\n[System] Giá cổ phiếu ${this.symbol} thay đổi từ ${this.price} -> ${newPrice}`);
        this.price = newPrice;
        this.notify(this.symbol, this.price);
    }

    getSymbol() {
        return this.symbol;
    }

    getPrice() {
        return this.price;
    }
}

module.exports = Stock;
