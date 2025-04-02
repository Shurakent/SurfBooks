package com.example.surfbook.presentation.screens.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.surfbook.R
import com.example.surfbook.presentation.navigation.Route


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val colors = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography
    BottomNavigation(
        backgroundColor = colors.background,
        contentColor = colors.primary,
        modifier = Modifier.border(width = 1.dp, color = colors.secondary)
    ) {

        BottomNavigationItem(
            modifier = Modifier.padding(10.dp),
            label = {Text(
                "Поиск",
                style = typo.labelMedium,
                color = if (currentRoute == Route.SearchScreen().route) colors.primary else colors.secondary
            )},
            selected = currentRoute == Route.SearchScreen().route,
            icon = {
                androidx.compose.material3.Icon(
                    painter = painterResource(id = R.drawable.ic_search_default), // Укажите ваш ресурс
                    contentDescription = "Поиск",
                    modifier = Modifier.size(size = 24.dp),
                    tint = if (currentRoute == Route.SearchScreen().route) colors.primary else colors.secondary
                )
            },
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
            modifier = Modifier.padding(10.dp),
            icon = {
                androidx.compose.material3.Icon(
                    painter = painterResource(id = R.drawable.ic_favorite_default), // Укажите ваш ресурс
                    contentDescription = "Избранное",
                    modifier = Modifier.size(size = 24.dp),
                    tint = if (currentRoute == Route.FavoritesScreen().route) colors.primary else colors.secondary
                )
            },
            label = {
                Text(
                    "Избранное",
                    style = typo.labelMedium,
                    color = if (currentRoute == Route.FavoritesScreen().route) colors.primary else colors.secondary
                )},
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
@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}
