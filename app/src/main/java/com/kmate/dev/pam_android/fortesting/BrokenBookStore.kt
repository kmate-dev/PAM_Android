package com.kmate.dev.pam_android.fortesting

import android.content.Context

class BrokenBookStore(
    context: Context
) {
    private val booksStorage = LocalBookStoreImpl(
        context.getSharedPreferences(
            "books_preferences",
            Context.MODE_PRIVATE
        )
    )

    fun getAllBooks() = booksStorage.getBooks()

    fun addBook(book: Book) {
        val books = booksStorage.getBooks().toMutableList()
        if (books.any { it.id == book.id }) {
            throw IllegalArgumentException("A book with the same ID already exists.")
        }
        books.add(book)
        booksStorage.saveBooks(books)
    }

    fun removeBook(bookId: Int): Boolean {
        val books = booksStorage.getBooks().toMutableList()
        val ret = books.removeIf{ it.id == bookId }
        booksStorage.saveBooks(books)
        return ret
    }

    fun calculateTotalValue(): Double {
        return booksStorage.getBooks().sumOf { it.price }
    }

    fun getBooksByAuthor(author: String): List<Book> {
        return booksStorage.getBooks().filter { it.author.equals(author, ignoreCase = true) }
    }
}