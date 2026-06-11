package com.example.praktam2_2417051012.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(

    BottomNavItem(
        Routes.HOME,
        "Home",
        Icons.Default.Home
    ),

    BottomNavItem(
        "search",
        "Search",
        Icons.Default.Search
    ),

    BottomNavItem(
        "planner",
        "Planner",
        Icons.Default.DateRange
    ),

    BottomNavItem(
        "profile",
        "Profile",
        Icons.Default.Person
    )
)