package com.example.booksshc.viewmodel

import androidx.lifecycle.ViewModel
import com.example.booksshc.data.Book
import com.example.booksshc.data.BooksData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class BookView(private val shopName: String) : ViewModel() {
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books.asStateFlow()

    init {
        loadBooks()
    }

    private fun loadBooks() {
        _books.value = BooksData.BookList
        Timber.i("Data masuk ke dalam list $shopName")
    }
}