package com.example.surfbook.presentation.screens.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun LoadingBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun EmptySearchBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(80.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Введите название книги, которую ищете",
            style = typography.titleMedium,
            color = colorScheme.surface,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun EmptyResponseBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(80.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "По вашему запросу ничего не найдено",
            style = typography.titleMedium,
            color = colorScheme.surface,
            textAlign = TextAlign.Center
        )
    }
}

