package com.example.bookshop.feature.book.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookshop.core.preferences.AppPreferences
import com.example.bookshop.feature.book.domain.usecase.*

class BookVMFactory(
    private val getPopularBooksUseCase: GetPopularBooksUseCase,
    private val getBookByIdUseCase: GetBookByIdUseCase,
    private val saveLastOpenedBookUseCase: SaveLastOpenedBookUseCase,
    private val getLastOpenedBookTitleUseCase: GetLastOpenedBookTitleUseCase,
    private val appPreferences: AppPreferences
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookVM::class.java)) {
            return BookVM(
                getPopularBooksUseCase,
                getBookByIdUseCase,
                saveLastOpenedBookUseCase,
                getLastOpenedBookTitleUseCase,
                appPreferences
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}