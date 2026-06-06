package com.example.bookshop.feature.book.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorDto(
    val name: String? = null
)

@Serializable
data class BuyLinkDto(
    val store: String? = null,
    val url: String? = null
)

@Serializable
data class BookDto(
    @SerialName("_id")
    val id: String? = null,

    val title: String? = null,

    @SerialName("author")
    val author: AuthorDto? = null,

    val publisher: String? = null,

    @SerialName("cover_image")
    val imageUrl: String? = null,

    @SerialName("summary")
    val description: String? = null,

    @SerialName("buy_links")
    val buyLinks: List<BuyLinkDto>? = null,

    @SerialName("publication_year")
    val publicationYear: Int? = null
)