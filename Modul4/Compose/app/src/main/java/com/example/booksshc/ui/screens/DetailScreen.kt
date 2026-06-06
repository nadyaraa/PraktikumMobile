package com.example.booksshc.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booksshc.data.Book

@Composable
fun DetailScreen(book: Book?) {
    if (book == null) return
    Column(modifier = Modifier.fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {

        Image(
            painterResource(book.imageId),
            null,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentScale = ContentScale.Crop
        )

        Column (modifier = Modifier.padding(20.dp)) {
            Text(book.title,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Text("Penulis: ${book.writer}",
                fontSize = 18.sp
            )
            Text("Penerbit: ${book.publisher}",
                fontSize = 18.sp
            )
            Text("Diterbitkan pada: ${book.publicationYear}",
                fontSize = 18.sp
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            Text("Deskripsi Buku",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(16.dp))

            Text(book.description,
                lineHeight = 22.sp,
                fontSize = 15.sp,
                textAlign = TextAlign.Justify
            )
        }
    }
}