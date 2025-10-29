package com.demate.laucherstupid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.demate.laucherstupid.ui.screens.DetailView
import com.demate.laucherstupid.ui.screens.HelloWorld

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppViews.SplashScreen.name,
        modifier = modifier
    ) {
        composable(AppViews.SplashScreen.name) {
            HelloWorld(navController)
        }

        composable(
            route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: "default"
            DetailView(
                detail = id,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("detail") {
            DetailView(
                detail = "sample",
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
