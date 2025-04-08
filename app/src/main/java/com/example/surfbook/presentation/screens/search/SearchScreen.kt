package com.example.surfbook.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.surfbook.presentation.screens.components.BooksGrid
import com.example.surfbook.presentation.screens.search.components.EmptyResponseBox
import com.example.surfbook.presentation.screens.search.components.EmptySearchBox
import com.example.surfbook.presentation.screens.search.components.LoadingBox
import com.example.surfbook.presentation.screens.search.components.SearchBar
import com.example.surfbook.presentation.screens.search.components.SearchItem


@Composable
fun SearchScreen(viewModel: SearchViewModel = viewModel()) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchResult by viewModel.searchResult.collectAsState()

    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                SearchBar(
                    searchQuery = searchQuery,
                    onQueryChange = { viewModel.updateQuery(it) }, // Обновляем текст в ViewModel
                    onSearch = { viewModel.fetchBooks() } // Выполняем поиск
                ) // Строка поиска
//                Spacer(modifier = Modifier.height(16.dp))
                when {
                    isLoading -> {
                        LoadingBox()
                    }
                    searchQuery.isEmpty() -> {
                        EmptySearchBox()
                    }
                    searchResult.isEmpty() -> {
                        EmptyResponseBox()
                    }
                    else -> {
                        BooksGrid(booksList = searchResult)
                    }
                }
            }
        }
    )
}

@Preview(apiLevel = 34)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}