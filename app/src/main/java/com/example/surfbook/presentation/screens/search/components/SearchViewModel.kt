package com.example.surfbook.presentation.screens.search.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surfbook.data.di.NetworkModule
import com.example.surfbook.domain.models.Book
import com.example.surfbook.domain.models.VolumeInfo
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _searchResults = mutableStateOf<List<Book>>(emptyList())
    val searchResults: State<List<Book>> = _searchResults

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun updateQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun searchBooks() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = NetworkModule.api.searchBooks(_searchQuery.value)
                _searchResults.value = response.items?.map {
                    Book(
                        id = it.id,
                        volumeInfo = VolumeInfo(
                            title = it.volumeInfo.title,
                            authors = it.volumeInfo.authors,
                            publishedDate = it.volumeInfo.publishedDate,
                            description = it.volumeInfo.description,
                            imageLinks = it.volumeInfo.imageLinks
                        )
                    )
                } ?: emptyList()
            } catch (e: Exception) {
                _error.value = "Ошибка выполнения запроса: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}