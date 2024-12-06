package com.kmate.dev.pam_android.fortesting

import android.content.SharedPreferences
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LocalBookStoreImpl(private val sharedPreferences: SharedPreferences) {
    companion object {
        private const val BOOKS_KEY = "books_key"
    }

    fun getBooks(): List<Book> {
        try {
            val booksListString = sharedPreferences.getString(
                BOOKS_KEY,
                ""
            )
            val booksList = Json.decodeFromString<List<Book>>(booksListString!!)
            return booksList
        } catch (e: Exception) {
            // Ignoring potential errors and returning empty list
            return emptyList()
        }
    }

    fun saveBooks(books: List<Book>) {
        try {
            sharedPreferences.edit().putString(
                BOOKS_KEY,
                Json.encodeToString(books)
            ).apply()
        } catch (e: Exception) {
            // Ignoring potential errors as this is just example app
        }
    }
}