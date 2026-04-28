package com.example.bookshop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(viewModel: BookView, onNavigateToDetail: (Int) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.books) { book ->
                    Box(modifier = Modifier.width(350.dp)) {
                        BookItem(book, onDetailClick = onNavigateToDetail)
                    }
                }
            }
        }

        item {
            Text(
                "Semua Buku",
                modifier = Modifier.padding(start = 20.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        items(viewModel.books) { book ->
            BookItem(book, onDetailClick = onNavigateToDetail)
        }
    }
}