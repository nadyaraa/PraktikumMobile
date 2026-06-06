package com.example.bookshop.feature.book.data.mapper

import com.example.bookshop.feature.book.data.local.BookEntity
import com.example.bookshop.feature.book.data.remote.dto.BookDto
import com.example.bookshop.feature.book.data.remote.dto.BookResponseDto
import com.example.bookshop.feature.book.domain.model.Book


fun BookDto.toBook(): Book {
    return Book(
        id = id ?: "",
        title = title ?: "Tanpa Judul",
        writer = author?.name ?: "Anonim",
        publisher = publisher ?: "Penerbit Tidak Diketahui",
        publicationYear = publicationYear ?: 0,
        description = description ?: "",
        imageUrl = imageUrl,
        olshopUrl = buyLinks?.firstOrNull()?.url ?: ""
    )
}

fun Book.toEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        writer = writer,
        publisher = publisher,
        publicationYear = publicationYear,
        description = description,
        imageUrl = imageUrl,
        olshopUrl = olshopUrl
    )
}

fun List<Book>.toEntityList(): List<BookEntity> {
    return map {
        it.toEntity()
    }
}

fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = title,
        writer = writer,
        publisher = publisher,
        publicationYear = publicationYear,
        description = description,
        imageUrl = imageUrl,
        olshopUrl = olshopUrl
    )
}

fun List<BookEntity>.toBookListFromEntity(): List<Book> {
    return map { it.toBook() }
}

fun BookResponseDto.toBookList(): List<Book> {
    return this.data.map { it.toBook() }
}