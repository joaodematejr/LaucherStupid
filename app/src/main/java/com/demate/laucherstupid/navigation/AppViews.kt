package com.demate.laucherstupid.navigation

enum class AppViews {
    SplashScreen,
    HomeScreen;

    companion object {
        fun fromRoute(route: String?): AppViews = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            HomeScreen.name -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized.")
        }

    }
}