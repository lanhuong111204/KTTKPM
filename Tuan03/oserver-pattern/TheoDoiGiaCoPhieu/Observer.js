class Observer {
    update(stockSymbol, price) {
        throw new Error("Method 'update(stockSymbol, price)' must be implemented.");
    }
}

module.exports = Observer;
