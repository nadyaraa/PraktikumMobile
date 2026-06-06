package com.example.bookshop.feature.book.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshop.core.common.UiState
import com.example.bookshop.core.network.ApiResult
import com.example.bookshop.core.preferences.AppPreferences
import com.example.bookshop.feature.book.domain.model.Book
import com.example.bookshop.feature.book.domain.usecase.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class BookVM(
    private val getPopularBooksUseCase: GetPopularBooksUseCase,
    private val getBookByIdUseCase: GetBookByIdUseCase,
    private val saveLastOpenedBookUseCase: SaveLastOpenedBookUseCase,
    private val getLastOpenedBookTitleUseCase: GetLastOpenedBookTitleUseCase,
    private val appPreferences: AppPreferences
) : ViewModel() {
    private val _booksState = MutableStateFlow<UiState<List<Book>>>(UiState.Idle)
    val booksState: StateFlow<UiState<List<Book>>> = _booksState.asStateFlow()

    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook: StateFlow<Book?> = _selectedBook.asStateFlow()

    private val _lastOpenedBookTitle = MutableStateFlow("")
    val lastOpenedBookTitle: StateFlow<String> = _lastOpenedBookTitle.asStateFlow()

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    init {
        loadLastOpenedBookTitle()
        loadBooks()
        _isDarkMode.value = appPreferences.isDarkModeEnabled()
    }

    fun loadBooks() {
        viewModelScope.launch {
            getPopularBooksUseCase().collect { result ->
                when (result) {
                    is ApiResult.Loading -> _booksState.value = UiState.Loading
                    is ApiResult.Success -> _booksState.value = UiState.Success(result.data)
                    is ApiResult.Error -> _booksState.value = UiState.Error(result.message)
                }
            }
        }
    }

    fun onBookClicked(book: Book) {
        saveLastOpenedBookUseCase(book.id, book.title)
        _lastOpenedBookTitle.value = book.title
        Timber.d("Buku ${book.title} terakhir dibuka")
    }

    fun selectBookById(bookId: String) {
        viewModelScope.launch {
            val book = getBookByIdUseCase(bookId)
            _selectedBook.value = book
        }
    }

    private fun loadLastOpenedBookTitle() {
        _lastOpenedBookTitle.value = getLastOpenedBookTitleUseCase()
    }

    fun toggleDarkMode(enabled: Boolean) {
        appPreferences.setDarkMode(enabled)
        _isDarkMode.value = enabled
    }
}