package com.example.bookshop

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookshop.data.Books
import androidx.core.net.toUri

@Composable
fun BookItem(book: Books, onDetailClick: (Int) -> Unit) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(180.dp)
        ) {
            Image(
                painter = painterResource(id = book.imageId),
                contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()
                .weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {

                        Text(book.title,
                            fontSize = 20.sp,
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

                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {

                        Text("Penulis: ${book.writer}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier
                                .weight(0.6f)
                                .padding(top = 4.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(book.publisher,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.weight(0.4f),
                            textAlign = TextAlign.End,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Text(book.description,
                        textAlign = TextAlign.Start,
                        fontSize = 11.sp,
                        maxLines = 5,
                        lineHeight = 14.sp,
                        modifier = Modifier.padding(top = 6.dp),
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Row(modifier = Modifier
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = {
                                onDetailClick(book.id) },
                            shape = RoundedCornerShape(12.dp)
                        ) {

                            Text("Detail",
                                fontSize = 11.sp)
                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        Button(
                            onClick = { context.startActivity(Intent(Intent.ACTION_VIEW,
                                book.olshopUrl.toUri())) },
                            shape = RoundedCornerShape(12.dp)
                        ) {

                            Text("Beli",
                                fontSize = 11.sp)
                        }
                    }
                }
            }
        }
    }
}