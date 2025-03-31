package com.example.surfbook.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.surfbook.presentation.screens.search.components.SearchBar
import com.example.surfbook.presentation.screens.search.components.SearchItem


@Composable
fun SearchScreen(viewModel: SearchViewModel = viewModel()) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchResult by viewModel.searchResult.collectAsState()
    Scaffold(
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                SearchBar(
                    searchQuery = searchQuery,
                    onQueryChange = { viewModel.updateQuery(it) }, // Обновляем текст в ViewModel
                    onSearch = { viewModel.fetchBooks() } // Выполняем поиск
                ) // Строка поиска
                SearchItem()
                Spacer(modifier = Modifier.height(16.dp))
                SearchApp()
                // Остальной контент
                LazyColumn {
                    items(listOf("Книга 1", "Книга 2", "Книга 3")) { book ->
                        Text(text = book, modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    )
}

//@Composable
//fun SimpleLazyColumnScreen(users: List<Book>) {
//    LazyColumn {
//        items(users) { user ->
//            PersonView(name = user.name)
//        }
//    }
//}

@Composable
fun SearchApp() {
    val viewModel: SearchViewModel = viewModel()
    val response by viewModel.searchResult.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = response,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.fetchBooks() }) {
            Text("Refresh Advice")
        }
    }
}
@Preview(apiLevel = 34)
@Composable
fun SearchAppPreview() {
    SearchApp()
}