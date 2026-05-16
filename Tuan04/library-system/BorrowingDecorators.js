// 5. Decorator Pattern: Mở rộng tính năng khi mượn sách

class Borrowing {
    getDescription() {
        return "Mượn sách tiêu chuẩn";
    }

    getDuration() {
        return 14; // 14 ngày mặc định
    }
}

class StandardBorrowing extends Borrowing {
    constructor(book) {
        super();
        this.book = book;
    }

    getDescription() {
        return `Mượn sách: ${this.book.title} (Bản tiêu chuẩn)`;
    }
}

class BorrowingDecorator extends Borrowing {
    constructor(borrowingRecord) {
        super();
        this.borrowingRecord = borrowingRecord;
    }

    getDescription() {
        return this.borrowingRecord.getDescription();
    }

    getDuration() {
        return this.borrowingRecord.getDuration();
    }
}

class ExtendedTimeDecorator extends BorrowingDecorator {
    getDescription() {
        return `${this.borrowingRecord.getDescription()} + [Gia hạn thời gian mượn]`;
    }

    getDuration() {
        return this.borrowingRecord.getDuration() + 7; // Gia hạn thêm 7 ngày
    }
}

class SpecialVersionDecorator extends BorrowingDecorator {
    constructor(borrowingRecord, versionType) {
        super(borrowingRecord);
        this.versionType = versionType;
    }

    getDescription() {
        return `${this.borrowingRecord.getDescription()} + [Phiên bản đặc biệt: ${this.versionType}]`;
    }
}

module.exports = { Borrowing, StandardBorrowing, BorrowingDecorator, ExtendedTimeDecorator, SpecialVersionDecorator };
