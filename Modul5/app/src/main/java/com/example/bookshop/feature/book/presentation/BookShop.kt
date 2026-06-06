package com.example.bookshop.feature.book.presentation

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshop.app.navigation.AppNavigation
import com.example.bookshop.core.database.AppDatabase
import com.example.bookshop.core.network.ApiClient
import com.example.bookshop.core.preferences.AppPreferences
import com.example.bookshop.feature.book.data.repository.BookPreferencesRepositoryImpl
import com.example.bookshop.feature.book.data.repository.BookRepositoryImpl
import com.example.bookshop.feature.book.domain.usecase.*
import com.example.bookshop.feature.book.presentation.viewModel.BookVM
import com.example.bookshop.feature.book.presentation.viewModel.BookVMFactory
import com.example.bookshop.ui.theme.BookShopTheme

@Composable
fun BookShop() {
    val context = LocalContext.current

    val database = remember { AppDatabase.getInstance(context) }
    val appPreferences = remember { AppPreferences(context) }

    val bookRepository = remember {
        BookRepositoryImpl(bookDao = database.bookDao(), apiService = ApiClient.bookService)
    }
    val bookPreferencesRepository = remember {
        BookPreferencesRepositoryImpl(appPreferences)
    }

    val viewModel: BookVM = viewModel(
        factory = BookVMFactory(
            getPopularBooksUseCase = GetPopularBooksUseCase(bookRepository),
            getBookByIdUseCase = GetBookByIdUseCase(bookRepository),
            saveLastOpenedBookUseCase = SaveLastOpenedBookUseCase(bookPreferencesRepository),
            getLastOpenedBookTitleUseCase = GetLastOpenedBookTitleUseCase(bookPreferencesRepository),
            appPreferences = appPreferences
        )
    )

    val isDarkMode by viewModel.isDarkMode.collectAsState()

    BookShopTheme(darkTheme = isDarkMode) {
        AppNavigation(viewModel = viewModel)
    }
}