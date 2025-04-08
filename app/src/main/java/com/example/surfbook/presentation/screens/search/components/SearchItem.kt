package com.example.surfbook.presentation.screens.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.surfbook.R
import com.example.surfbook.domain.model.ImageLinks
import com.example.surfbook.domain.model.VolumeInfo

@Composable
fun SearchItem(volumeInfo: VolumeInfo) {
    val typo = MaterialTheme.typography
    val colors = MaterialTheme.colorScheme
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ){
        Column() {
            val imageUrl =  volumeInfo.imageLinks?.thumbnail

            if (imageUrl != null){
//                Spacer(modifier = Modifier.height(8.dp))
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
            volumeInfo.authors?.let {
                Text(
                    text = it.joinToString(", "),
                    style = typo.labelSmall,
                    color = colors.tertiary
                )
            }
            Text(
                text = volumeInfo.title ?: "Без названия",
                style = typo.labelSmall,
                color = colors.surface
            )
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
