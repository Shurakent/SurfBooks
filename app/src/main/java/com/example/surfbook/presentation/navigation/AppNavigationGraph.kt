package com.example.surfbook.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.surfbook.presentation.screens.favorite.FavoritesScreen
import com.example.surfbook.presentation.screens.search.SearchScreen

@Composable
fun AppNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Route.SearchScreen().route, // Стартовый экран — SearchScreen
        modifier = modifier.fillMaxSize()
    ) {
        // Экран поиска (SearchScreen)
        composable(
            route = Route.SearchScreen().route
        ) {
            SearchScreen()
        }

        // Экран избранного (FavoritesScreen)
        composable(
            route = Route.FavoritesScreen().route
        ) {
            FavoritesScreen()
        }
    }
}

