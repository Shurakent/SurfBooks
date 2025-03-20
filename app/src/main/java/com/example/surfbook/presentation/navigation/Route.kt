package com.example.surfbook.presentation.navigation

sealed class Route {
    data class SearchScreen(val route: String = "search") : Route()
    data class FavoritesScreen(val route: String = "favorites") : Route()
}