const Observer = require('./Observer');

class Investor extends Observer {
    constructor(name) {
        super();
        this.name = name;
    }

    update(stockSymbol, price) {
        console.log(`Thông báo cho nhà đầu tư ${this.name}: Cổ phiếu ${stockSymbol} đã cập nhật giá mới là ${price}`);
    }
}

module.exports = Investor;
