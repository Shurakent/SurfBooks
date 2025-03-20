package com.example.surfbook.presentation.screens.components

import android.annotation.SuppressLint
import android.content.res.Resources.Theme
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.surfbook.R
import com.example.surfbook.presentation.navigation.Route
import com.example.surfbook.presentation.ui.theme.Blue80
import com.example.surfbook.presentation.ui.theme.SurfBookTheme
import com.example.surfbook.presentation.ui.theme.White100

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val colors = MaterialTheme.colorScheme

    BottomNavigation(
        backgroundColor = colors.background,
        contentColor = colors.primary
    ) {

        BottomNavigationItem(
            icon = {
                androidx.compose.material3.Icon(
                    painter = painterResource(id = R.drawable.ic_search_grey), // Укажите ваш ресурс
                    contentDescription = "Поиск",
                    tint = colors.tertiary
                )
            },
            label = { Text("Поиск") },
            selected = currentRoute == Route.SearchScreen().route,
            onClick = {
                navController.navigate(Route.SearchScreen().route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Избранное") },
            label = { Text("Избранное") },
            selected = currentRoute == Route.FavoritesScreen().route,
            onClick = {
                navController.navigate(Route.FavoritesScreen().route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
