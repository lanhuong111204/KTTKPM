// 3. Strategy Pattern: Chiến lược tìm kiếm sách

class SearchStrategy {
    search(books, keyword) {
        throw new Error("Method 'search()' must be implemented.");
    }
}

class SearchByName extends SearchStrategy {
    search(books, keyword) {
        return books.filter(book => book.title.toLowerCase().includes(keyword.toLowerCase()));
    }
}

class SearchByAuthor extends SearchStrategy {
    search(books, keyword) {
        return books.filter(book => book.author.toLowerCase().includes(keyword.toLowerCase()));
    }
}

class SearchByCategory extends SearchStrategy {
    search(books, keyword) {
        return books.filter(book => book.category.toLowerCase().includes(keyword.toLowerCase()));
    }
}

module.exports = { SearchStrategy, SearchByName, SearchByAuthor, SearchByCategory };
