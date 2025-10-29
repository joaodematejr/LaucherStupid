package com.demate.laucherstupid.navigation

enum class AppViews {
    SplashScreen;

    companion object {
        fun fromRoute(route: String?): AppViews = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            else -> throw IllegalArgumentException("Route $route is not recognized.")
        }

    }
}