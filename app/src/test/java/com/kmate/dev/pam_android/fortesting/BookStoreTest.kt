package com.kmate.dev.pam_android.fortesting

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BookStoreTest {
    private lateinit var store: BookStore
    private val book1 = Book(
        id = 0,
        title = "Harry Potter",
        author = "J.K. Rowling",
        price = 20.0
    )
    private val book2 = Book(
        id = 1,
        title = "Harry Potter 2",
        author = "J.K. Rowling",
        price = 30.0
    )
    private val book3 = Book(
        id = 2,
        title = "Harry Potter 3",
        author = "J.K. Rowling",
        price = 50.0
    )

    @BeforeEach
    fun setUp() {
        store = BookStore(
            initialBooks = listOf(
                book1, book2, book3
            ).toMutableList()
        )
    }

    @AfterEach
    fun tearDown() {
        // Not needed for now
    }

    @Test
    fun `removeBook removes book with matching id`() {
        //SetUp
        val expectedBooks = listOf(
            book2, book3
        )

        //Actions
        store.removeBook(bookId = 0)

        //Verification
        assertEquals(
            expectedBooks,
            store.getAllBooks()
        )
    }

    @ParameterizedTest
    @CsvSource("true, 2", "false, 100", "true, 0", "false, -5")
    fun `removeBook returns true only if book has been removed`(
        expectedReturnValue: Boolean,
        bookId: Int
    ) {
        val returnValue = store.removeBook(bookId)

        assertEquals(expectedReturnValue, returnValue)
    }
}