package com.example.bookshop.feature.book.data.repository

import com.example.bookshop.core.network.ApiResult
import com.example.bookshop.core.network.safeApiCall
import com.example.bookshop.feature.book.data.local.BookDao
import com.example.bookshop.feature.book.data.mapper.*
import com.example.bookshop.feature.book.data.remote.BookApiService
import com.example.bookshop.feature.book.domain.model.Book
import com.example.bookshop.feature.book.domain.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class BookRepositoryImpl(
    private val bookDao: BookDao,
    private val apiService: BookApiService
) : BookRepository {

    override fun getBooks(): Flow<ApiResult<List<Book>>> = flow {
        emit(ApiResult.Loading)

        val cachedBooks = bookDao.getBooks()
            .first()
            .toBookListFromEntity()

        if (cachedBooks.isNotEmpty()) {
            Timber.d("Menampilkan data dari Cache Room")
            emit(ApiResult.Success(cachedBooks))
        }

        Timber.d("Mengambil data terbaru dari API")
        val result = safeApiCall {
            apiService.getBooks()
        }

        when (result) {
            is ApiResult.Success -> {
                val remoteBooks = result.data.toBookList()

                bookDao.clearBooks()
                bookDao.insertBooks(remoteBooks.toEntityList())

                Timber.d("Cache Room diperbarui dengan data dari API")

                emit(ApiResult.Success(remoteBooks))
            }

            is ApiResult.Error -> {
                Timber.e("Gagal mengambil data API ${result.message}")
                if (cachedBooks.isEmpty()) {
                    emit(ApiResult.Error(result.message, result.throwable))
                } else {
                    Timber.d("API Gagal, menggunakan data Cache yang tersedia")
                }
            }
            else -> {}
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getBookById(bookId: String): Book? {
       return bookDao.getBookById(bookId)?.toBook()
    }
}