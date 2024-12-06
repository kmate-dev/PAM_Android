package com.kmate.dev.pam_android.fortesting

class BookStore(
    initialBooks: List<Book>
) {
    private val books: MutableList<Book> = initialBooks.toMutableList()

    fun getAllBooks() = books.toList()

    fun addBook(book: Book) {
        if (books.any { it.id == book.id }) {
            throw IllegalArgumentException("A book with the same ID already exists.")
        }
        books.add(book)
    }

    fun removeBook(bookId: Int): Boolean {
        return books.removeIf { it.id == bookId }
    }

    fun calculateTotalValue(): Double {
        return books.sumOf { it.price }
    }

    fun getBooksByAuthor(author: String): List<Book> {
        return books.filter { it.author.equals(author, ignoreCase = true) }
    }

    fun isAuthorInCollection(author: String): Boolean {
        return books.find { it.author.equals(author, ignoreCase = true) } != null
    }
}

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val price: Double
)