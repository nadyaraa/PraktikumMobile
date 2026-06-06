package com.example.bookshop.feature.book.domain.usecase

import com.example.bookshop.feature.book.domain.repository.BookPreferencesRepository

class SaveLastOpenedBookUseCase(
    private val repository: BookPreferencesRepository
) {
    operator fun invoke(bookId: String, bookTitle: String) {
        repository.saveLastOpenedBook(bookId, bookTitle)
    }
}