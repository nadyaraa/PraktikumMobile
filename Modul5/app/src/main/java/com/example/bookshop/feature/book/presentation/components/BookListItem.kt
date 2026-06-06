package com.example.bookshop.feature.book.presentation.components

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.example.bookshop.feature.book.domain.model.Book

@Composable
fun BookListItem(book: Book, onDetailClick: (Book) -> Unit) {
    val context = LocalContext.current

    Card( shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(modifier = Modifier.padding(10.dp).fillMaxWidth().height(180.dp)) {
            AsyncImage(model = book.imageUrl,
                contentDescription = book.title,
                modifier = Modifier.width(120.dp).fillMaxWidth().clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(8.dp).fillMaxWidth().weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(book.title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
                        Text(book.publicationYear.toString(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Light
                        )
                    }

                    Text("Penulis: ${book.writer}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Light,
                        maxLines = 1
                    )
                    Text(book.description,
                        fontSize = 11.sp,
                        maxLines = 4,
                        lineHeight = 14.sp,
                        modifier = Modifier.padding(top = 6.dp),
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Button(onClick = {onDetailClick(book)},
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text("Detail", fontSize = 11.sp)
                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        Button(onClick = {
                            context.startActivity(Intent(Intent.ACTION_VIEW, book.olshopUrl.toUri()))
                        },
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text("Beli", fontSize = 11.sp)
                        }
                    }
                }
            }
        }
    }
}