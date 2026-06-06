package com.example.bookshop.feature.book.domain.usecase

import com.example.bookshop.core.network.ApiResult
import com.example.bookshop.feature.book.domain.model.Book
import com.example.bookshop.feature.book.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class GetPopularBooksUseCase(
    private val repository: BookRepository
) {
    operator fun invoke(): Flow<ApiResult<List<Book>>> {
        return repository.getBooks()
    }
}