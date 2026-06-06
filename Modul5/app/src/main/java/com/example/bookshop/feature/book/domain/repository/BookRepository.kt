package com.example.bookshop.feature.book.domain.repository

import com.example.bookshop.core.network.ApiResult
import com.example.bookshop.feature.book.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getBooks(): Flow<ApiResult<List<Book>>>
    suspend fun getBookById(bookId: String): Book?
}