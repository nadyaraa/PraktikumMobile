package com.example.booksshx.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookVMFactory(private val category: String) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BookView::class.java)) {
            return BookView(category) as T
        }

        throw IllegalArgumentException("ViewModel tidak dikenal")
    }
}