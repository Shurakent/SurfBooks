package com.example.surfbook.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surfbook.data.di.RetrofitInstance
import kotlinx.coroutines.launch

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    // Состояние для хранения текста поиска
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Состояние для хранения результата поиска
    private val _searchResult = MutableStateFlow("")
    val searchResult: StateFlow<String> = _searchResult.asStateFlow()

    // Метод для обновления текста поиска
    fun updateQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }

    // Метод для выполнения поиска
    fun fetchBooks() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.searchBooks(_searchQuery.value)
                _searchResult.value = response.items?.get(0)?.volumeInfo?.title ?: "Ничего не найдено"
            } catch (e: Exception) {
                _searchResult.value = "Ошибка: ${e.message}"
            }
        }
    }
}