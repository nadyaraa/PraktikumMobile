package com.example.bookshop.feature.book.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val writer: String,
    val publisher: String,
    val publicationYear: Int,
    val description: String,
    val imageUrl: String?,
    val olshopUrl: String
)