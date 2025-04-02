package com.example.surfbook.presentation.screens.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
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


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val colors = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography

    NavigationBar(
        containerColor = colors.background,
        contentColor = colors.primary,
        modifier = Modifier.border(width = 1.dp, color = colors.secondary)
    ) {
        NavBarItems.BarItems.forEach {navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {saveState = true}
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    androidx.compose.material3.Icon(
                        painter = painterResource(id = navItem.image),
                        contentDescription = "Поиск",
                        modifier = Modifier.size(size = 24.dp),
                    )
                },
                label = {Text(
                    navItem.title,
                    style = typo.labelMedium,
                )},
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colors.primary,
                    selectedTextColor = colors.primary,
                    unselectedIconColor = colors.secondary,
                    unselectedTextColor = colors.secondary,
                    indicatorColor = colors.background
                )
            )
        }
    }
}

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Поиск",
            image = R.drawable.ic_search_default,
            route = "search"
        ),
        BarItem(
            title = "Избранное",
            image = R.drawable.ic_favorite_default,
            route = "favorites"
        )
    )
}

data class BarItem(
    val title: String,
    val image: Int,
    val route: String
)
@Preview(apiLevel=34)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}
