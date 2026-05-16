const Library = require('./Library');
const { BookFactory } = require('./Books');
const { SearchByName, SearchByAuthor, SearchByCategory } = require('./SearchStrategies');
const { Subscriber } = require('./Observers');
const { StandardBorrowing, ExtendedTimeDecorator, SpecialVersionDecorator } = require('./BorrowingDecorators');

// 1. Khởi tạo Singleton Library
const library = Library.getInstance();

// Đảm bảo chỉ có một thể hiện duy nhất
const anotherLibraryInstance = Library.getInstance();
console.log(`[Singleton Check] library === anotherLibraryInstance: ${library === anotherLibraryInstance}\n`);

// Đăng ký người dùng theo dõi (Observer)
const user1 = new Subscriber("Độc giả An");
const librarian = new Subscriber("Thủ thư Bình");
library.addObserver(user1);
library.addObserver(librarian);

// 2. Thêm sách bằng Factory Method
console.log("=== THÊM SÁCH MỚI ===");
const book1 = BookFactory.createBook("paper", "Clean Code", "Robert C. Martin", "Programming");
const book2 = BookFactory.createBook("ebook", "Design Patterns", "Gang of Four", "Programming");
const book3 = BookFactory.createBook("audio", "Đắc Nhân Tâm", "Dale Carnegie", "Psychology");

library.addBook(book1);
library.addBook(book2);
library.addBook(book3);

// 3. Tìm kiếm sách (Strategy Pattern)
console.log("\n=== TÌM KIẾM SÁCH ===");
// Tìm theo tên
library.setSearchStrategy(new SearchByName());
console.log("Tìm theo tên 'Code':", library.searchBooks("Code").map(b => b.title));

// Tìm theo tác giả
library.setSearchStrategy(new SearchByAuthor());
console.log("Tìm theo tác giả 'Gang of Four':", library.searchBooks("Gang of Four").map(b => b.title));

// Tìm theo thể loại
library.setSearchStrategy(new SearchByCategory());
console.log("Tìm theo thể loại 'Psychology':", library.searchBooks("Psychology").map(b => b.title));

// 4. Mượn sách (Decorator Pattern)
console.log("\n=== MƯỢN SÁCH ===");
// Mượn sách tiêu chuẩn
let myBorrowing = new StandardBorrowing(book1);
console.log(`1. ${myBorrowing.getDescription()} | Thời gian: ${myBorrowing.getDuration()} ngày`);

// Mượn sách và xin gia hạn thêm
myBorrowing = new ExtendedTimeDecorator(myBorrowing);
console.log(`2. ${myBorrowing.getDescription()} | Thời gian: ${myBorrowing.getDuration()} ngày`);

// Mượn sách, xin gia hạn và yêu cầu phiên bản chữ nổi Braille
myBorrowing = new SpecialVersionDecorator(myBorrowing, "Sách chữ nổi Braille");
console.log(`3. ${myBorrowing.getDescription()} | Thời gian: ${myBorrowing.getDuration()} ngày`);

// Thông báo quá hạn (Observer Pattern)
console.log("\n=== THÔNG BÁO QUÁ HẠN ===");
library.notifyOverdueBook("Clean Code", "Độc giả An");
