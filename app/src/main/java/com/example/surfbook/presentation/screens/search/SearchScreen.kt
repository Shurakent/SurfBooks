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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
                Spacer(modifier = Modifier.height(16.dp))
                // Остальной контент
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(searchResult) { book ->
                        SearchItem(volumeInfo = book.volumeInfo)
                    }
                }
//                LazyColumn {
//                    items(searchResult) { book ->
//                        SearchItem(volumeInfo = book.volumeInfo)
//                    }
//                }
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

@Preview(apiLevel = 34)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}