package com.example.bookshop.feature.book.domain.model

data class Book(
    val id: String,
    val title: String,
    val writer: String,
    val publisher: String,
    val publicationYear: Int,
    val description: String,
    val imageUrl: String?,
    val olshopUrl: String
)