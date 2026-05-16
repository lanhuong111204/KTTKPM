class Subject {
    constructor() {
        this.observers = [];
    }

    attach(observer) {
        this.observers.push(observer);
    }

    detach(observer) {
        this.observers = this.observers.filter(obs => obs !== observer);
    }

    notify(stockSymbol, price) {
        this.observers.forEach(observer => observer.update(stockSymbol, price));
    }
}

module.exports = Subject;
