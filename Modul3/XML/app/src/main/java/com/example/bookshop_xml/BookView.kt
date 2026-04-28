package com.example.bookshop_xml

import androidx.lifecycle.ViewModel
import com.example.bookshop_xml.data.Books
import com.example.bookshop_xml.data.BooksData

class BookView : ViewModel() {
    val books: List<Books> = BooksData.BookList
}