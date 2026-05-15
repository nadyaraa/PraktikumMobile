package com.example.booksshc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookVMFactory(private val shopName: String) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookView::class.java)) {
            return BookView(shopName) as T
        }

        throw IllegalArgumentException("ViewModel Tidak Dikenal")
    }
}