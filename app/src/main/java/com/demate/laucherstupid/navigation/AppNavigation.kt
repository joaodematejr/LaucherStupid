package com.demate.laucherstupid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.demate.laucherstupid.ui.screens.home.HomeScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppViews.HomeScreen.name,
        modifier = modifier
    ) {
        composable(AppViews.HomeScreen.name) {
            HomeScreen()
        }
    }
}
