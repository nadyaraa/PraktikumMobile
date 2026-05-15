package com.example.booksshx.viewmodel

import androidx.lifecycle.ViewModel
import com.example.booksshx.data.Books
import com.example.booksshx.data.BooksData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class BookView(val shopName: String) : ViewModel() {
    private val _booksState = MutableStateFlow<List<Books>>(emptyList())
    val booksState: StateFlow<List<Books>> = _booksState

    private val _navigateToDetail = MutableStateFlow<Int?>(null)
    val navigateToDetail: StateFlow<Int?> = _navigateToDetail

    init {
        loadBooks()
    }

    private fun loadBooks() {
        _booksState.value = BooksData.BookList
        Timber.d("Data item masuk ke dalam list $shopName")
    }

    fun onBookClicked(id: Int) {
        _navigateToDetail.value = id
    }

    fun onNavigatedToDetail() {
        _navigateToDetail.value = null
    }
}