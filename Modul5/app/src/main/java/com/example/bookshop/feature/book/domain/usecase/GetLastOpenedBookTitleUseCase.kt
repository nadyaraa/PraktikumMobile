package com.example.bookshop.feature.book.domain.usecase

import com.example.bookshop.feature.book.domain.repository.BookPreferencesRepository

class GetLastOpenedBookTitleUseCase(
    private val repository: BookPreferencesRepository
) {
    operator fun invoke(): String {
        return repository.getLastOpenedBookTitle()
    }
}