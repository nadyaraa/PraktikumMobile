package com.example.bookshop.feature.book.domain.usecase

import com.example.bookshop.feature.book.domain.model.Book
import com.example.bookshop.feature.book.domain.repository.BookRepository

class GetBookByIdUseCase(
    private val repository: BookRepository
) {
    suspend operator fun invoke(bookId: String): Book? {
        return repository.getBookById(bookId)
    }
}