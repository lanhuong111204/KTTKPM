// 1. Singleton Pattern: Quản lý thư viện duy nhất
const { SearchByName } = require('./SearchStrategies');

class Library {
    constructor() {
        if (Library.instance) {
            return Library.instance;
        }
        
        this.books = [];
        this.observers = [];
        this.searchStrategy = new SearchByName(); // Chiến lược mặc định
        
        Library.instance = this;
    }

    static getInstance() {
        if (!Library.instance) {
            Library.instance = new Library();
        }
        return Library.instance;
    }

    // --- Chức năng Observer ---
    addObserver(observer) {
        this.observers.push(observer);
    }

    removeObserver(observer) {
        this.observers = this.observers.filter(obs => obs !== observer);
    }

    notifyObservers(message) {
        this.observers.forEach(obs => obs.update(message));
    }

    // --- Chức năng Quản lý sách ---
    addBook(book) {
        this.books.push(book);
        this.notifyObservers(`Có sách mới: "${book.title}" của tác giả ${book.author}`);
    }

    getBooks() {
        return this.books;
    }

    // --- Chức năng Strategy (Tìm kiếm) ---
    setSearchStrategy(strategy) {
        this.searchStrategy = strategy;
    }

    searchBooks(keyword) {
        return this.searchStrategy.search(this.books, keyword);
    }

    // --- Giả lập thông báo hết hạn sách ---
    notifyOverdueBook(bookTitle, userName) {
        this.notifyObservers(`CẢNH BÁO: Sách "${bookTitle}" do ${userName} mượn đã quá hạn!`);
    }
}

module.exports = Library;
