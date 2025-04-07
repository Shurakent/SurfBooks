package com.example.surfbook.presentation.screens.search.components

import android.support.wearable.watchface.decomposition.ImageComponent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentDataType.Companion.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.surfbook.R
import com.example.surfbook.domain.model.ImageLinks
import com.example.surfbook.domain.model.VolumeInfo

@Composable
fun SearchItem(volumeInfo: VolumeInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            val imageUrl =  volumeInfo.imageLinks?.thumbnail
            val imageLoader = ImageLoader.Builder(LocalContext.current)
                .build()
            if (imageUrl != null){
//                Image(
//                    painter = rememberAsyncImagePainter(
//                        model = "http://books.google.com/books/content?id=HN0HAQAAMAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api",
//                        imageLoader = imageLoader
//                    ),
//                    contentDescription = "Обложка книги",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(180.dp)
//                        .clip(MaterialTheme.shapes.medium)
//                )
                Spacer(modifier = Modifier.height(8.dp))
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(MaterialTheme.shapes.medium),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(volumeInfo.imageLinks.thumbnail.replace("http", "https"))
                        .crossfade(true)
                        .build(),
                    error = painterResource(id = R.drawable.cross_grey),
                    placeholder = painterResource(id = R.drawable.loading_spin_icon),
                    contentDescription = "Обложка",
                    contentScale = ContentScale.Crop
                )
            }
            Text(text = volumeInfo.title, fontSize = 16.sp)
            volumeInfo.authors?.let {
                Text(text = it.joinToString(", "), fontSize = 13.sp)
            }
            volumeInfo.publishedDate?.let {
                Text(text = "Год: $it", fontSize = 12.sp)
            }
        }
    }
}
@Preview(apiLevel = 34)
@Composable
fun SearchItemPreview(){
    SearchItem(volumeInfo = VolumeInfo(
        title = "Гарри Поттер и философский камень",
        authors = listOf("J. K. Rowling"),
        publishedDate = "2003",
        description =  "Это - сказка, рассказанная зимней ночью. Веселое и грустное повествование о вражде и дружбе, магии и смекалке, благородстве и предательстве, любви и ненависти, между которыми один шаг",
        imageLinks = ImageLinks(
            thumbnail = "http://books.google.com/books/content?id=HN0HAQAAMAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        )
    )
    )
}
