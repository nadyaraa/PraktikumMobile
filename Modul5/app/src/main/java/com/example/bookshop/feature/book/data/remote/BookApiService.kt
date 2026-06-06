package com.example.bookshop.feature.book.data.remote

import com.example.bookshop.feature.book.data.remote.dto.BookResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {
    @GET("book")
    suspend fun getBooks(
        @Query("sort") urut: String? = null,
        @Query("page") angka: Int? = null,
        @Query("year") tahun: Int? = null,
        @Query("genre") genre: String? = null,
    ): BookResponseDto
}