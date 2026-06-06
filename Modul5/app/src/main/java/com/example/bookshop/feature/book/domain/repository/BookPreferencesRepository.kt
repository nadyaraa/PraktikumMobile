package com.example.bookshop.feature.book.domain.repository

interface BookPreferencesRepository {
    fun saveLastOpenedBook(bookId: String, bookTitle: String)
    fun getLastOpenedBookTitle(): String
}