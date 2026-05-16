// 4. Observer Pattern: Thông báo khi có sách mới hoặc sách hết hạn

class Observer {
    update(message) {
        throw new Error("Method 'update()' must be implemented.");
    }
}

class Subscriber extends Observer {
    constructor(name) {
        super();
        this.name = name;
    }

    update(message) {
        console.log(`[Thông báo cho ${this.name}]: ${message}`);
    }
}

module.exports = { Observer, Subscriber };
