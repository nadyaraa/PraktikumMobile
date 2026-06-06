package com.example.bookshop.feature.book.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    fun getBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE id = :bookId LIMIT 1")
    suspend fun getBookById(bookId: String): BookEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<BookEntity>)

    @Query("DELETE FROM books")
    suspend fun clearBooks()
}