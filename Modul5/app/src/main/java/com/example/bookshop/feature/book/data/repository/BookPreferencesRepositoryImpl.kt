package com.example.bookshop.feature.book.data.repository

import com.example.bookshop.core.preferences.AppPreferences
import com.example.bookshop.feature.book.domain.repository.BookPreferencesRepository

class BookPreferencesRepositoryImpl(
    private val appPreferences: AppPreferences
) : BookPreferencesRepository {

    override fun saveLastOpenedBook(bookId: String, bookTitle: String) {
        appPreferences.saveLastOpenedBook(bookId, bookTitle)
    }

    override fun getLastOpenedBookTitle(): String {
        return appPreferences.getLastOpenedBookTitle()
    }
}