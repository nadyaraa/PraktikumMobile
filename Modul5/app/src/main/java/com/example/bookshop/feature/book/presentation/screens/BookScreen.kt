package com.example.bookshop.feature.book.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.bookshop.core.common.UiState
import com.example.bookshop.feature.book.presentation.components.BookListItem
import com.example.bookshop.feature.book.presentation.viewModel.BookVM

@Composable
fun BookScreen(viewModel: BookVM, onNavigateToDetail: (String) -> Unit) {
    val state by viewModel.booksState.collectAsState()
    val lastTitle by viewModel.lastOpenedBookTitle.collectAsState()

    when (val result = state) {
        is UiState.Loading -> Box(Modifier.fillMaxSize(), Alignment.Center) {
            CircularProgressIndicator()
        }
        is UiState.Error -> Box(Modifier.fillMaxSize(), Alignment.Center) {
            Text(result.message)
        }
        is UiState.Success -> {
            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(8.dp)) {
                item {
                    LazyRow(contentPadding = PaddingValues(horizontal = 4.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(result.data.take(5)) {
                            book ->
                            Box(modifier = Modifier.width(350.dp)) {
                                BookListItem(book, onDetailClick = {
                                    viewModel.onBookClicked(it)
                                    onNavigateToDetail(it.id)
                                })
                            }
                        }
                    }
                }

                item {
                    Column(Modifier.padding(16.dp)) {
                        Text("Semua Buku", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        if (lastTitle.isNotEmpty()) {
                            Text("Terakhir dilihat $lastTitle", fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }

                items(result.data) {
                    book ->
                    BookListItem(book, onDetailClick = {
                        viewModel.onBookClicked(it)
                        onNavigateToDetail(it.id)
                    })
                }
            }
        }

        else -> {}
    }
}