package com.example.bookshop.feature.book.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BookResponseDto(
    @SerialName("books")
    val data: List<BookDto>
)