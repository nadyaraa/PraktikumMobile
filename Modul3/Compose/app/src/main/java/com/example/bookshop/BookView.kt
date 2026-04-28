package com.example.bookshop

import androidx.lifecycle.ViewModel
import com.example.bookshop.data.Books
import com.example.bookshop.data.BooksData

class BookView : ViewModel() {
    val books: List<Books> = BooksData.BookList
}