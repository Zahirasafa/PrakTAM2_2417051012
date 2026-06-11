package com.example.praktam2_2417051012.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object ForgotPassword : Screen("forgot_password")
    object Home : Screen("home")
    object Search : Screen("search")
    object Planner : Screen("planner")
    object Profile : Screen("profile")
    object Detail : Screen("detail/{travelId}") {
        fun createRoute(travelId: String) = "detail/$travelId"
    }
}
