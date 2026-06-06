package com.example.bookshop.feature.book.presentation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import coil3.compose.AsyncImage
import com.example.bookshop.feature.book.presentation.viewModel.BookVM

@Composable
fun BookDetailScreen(bookId: String, viewModel: BookVM) {
    LaunchedEffect(bookId) {
        viewModel.selectBookById(bookId)
    }

    val book by viewModel.selectedBook.collectAsState()
    book?.let {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            AsyncImage(model = it.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(400.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(20.dp)) {
                Text(it.title, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Text("Penulis: ${it.writer}", fontSize = 18.sp)
                Text("Penerbit: ${it.publisher}", fontSize = 18.sp)
                Text("Tahun Terbit: ${it.publicationYear}", fontSize = 18.sp)

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                Text("Deskripsi Buku", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(16.dp))
                Text(it.description, lineHeight = 22.sp, fontSize = 15.sp, textAlign = TextAlign.Justify)
            }
        }
    }
}