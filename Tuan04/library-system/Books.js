// 2. Factory Method Pattern: Quản lý việc tạo ra các loại sách khác nhau

class Book {
    constructor(title, author, category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }
    
    getInfo() {
        return `${this.title} - ${this.author} (${this.category})`;
    }
}

class PaperBook extends Book {
    constructor(title, author, category) {
        super(title, author, category);
        this.type = "Paper Book";
    }
}

class EBook extends Book {
    constructor(title, author, category) {
        super(title, author, category);
        this.type = "E-Book";
    }
}

class AudioBook extends Book {
    constructor(title, author, category) {
        super(title, author, category);
        this.type = "Audio Book";
    }
}

class BookFactory {
    static createBook(type, title, author, category) {
        switch (type.toLowerCase()) {
            case 'paper':
                return new PaperBook(title, author, category);
            case 'ebook':
                return new EBook(title, author, category);
            case 'audio':
                return new AudioBook(title, author, category);
            default:
                throw new Error("Loại sách không hợp lệ");
        }
    }
}

module.exports = { Book, PaperBook, EBook, AudioBook, BookFactory };
