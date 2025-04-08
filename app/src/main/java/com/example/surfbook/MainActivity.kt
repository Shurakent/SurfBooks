package com.example.surfbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.surfbook.presentation.navigation.AppNavigationGraph
import com.example.surfbook.presentation.screens.components.BottomNavigationBar
import com.example.surfbook.presentation.ui.theme.SurfBookTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { SurfBookTheme{
            App()
        } }
    }

    @Composable
    fun App() {
        val navController = rememberNavController()
        SurfBookTheme {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(navController = navController)
                }
            ) { innerPadding ->
                AppNavigationGraph(
                    navController = navController,
                    modifier = Modifier.
                    padding(innerPadding)
                    .systemBarsPadding()
                )
            }
        }

    }
}
