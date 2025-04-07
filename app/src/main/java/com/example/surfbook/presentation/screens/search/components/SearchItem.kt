package com.example.surfbook.presentation.screens.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.surfbook.domain.model.VolumeInfo

@Composable
fun SearchItem(volumeInfo: VolumeInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountBox,
                contentDescription = "Book Icon",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(text = volumeInfo.title, fontSize = 18.sp)
                volumeInfo.authors?.let {
                    Text(text = it.joinToString(", "), fontSize = 14.sp)
                }
                volumeInfo.publishedDate?.let {
                    Text(text = "Год: $it", fontSize = 12.sp)
                }
            }
        }
    }
}
