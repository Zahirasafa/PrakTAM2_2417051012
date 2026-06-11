package com.example.praktam2_2417051012.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.praktam2_2417051012.navigation.Routes
import com.example.praktam2_2417051012.ui.home.HomeScreen
import com.example.praktam2_2417051012.ui.search.SearchScreen
import com.example.praktam2_2417051012.ui.planner.PlannerScreen
import com.example.praktam2_2417051012.ui.profile.ProfileScreen

data class BottomItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun MainContainerScreen(
    parentNavController: NavHostController
) {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    val items = listOf(
        BottomItem("Home", Icons.Default.Home),
        BottomItem("Cari", Icons.Default.Search),
        BottomItem("Rencana", Icons.Default.DateRange),
        BottomItem("Profil", Icons.Default.Person)
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = {
                            Text(item.title)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.secondary,
                            selectedTextColor = MaterialTheme.colorScheme.secondary,
                            indicatorColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f),
                            unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f)
                        )
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier.padding(padding)
        ) {
            when (selectedIndex) {
                0 -> HomeScreen(
                    onDetailClick = { travelName ->
                        parentNavController.navigate("${Routes.DETAIL}/$travelName")
                    },
                    onRegionClick = { regionName ->
                        parentNavController.navigate("${Routes.REGION}/$regionName")
                    }
                )
                1 -> SearchScreen(
                    onDetailClick = { travelName ->
                        parentNavController.navigate("${Routes.DETAIL}/$travelName")
                    },
                    onUserClick = { username ->
                        parentNavController.navigate("${Routes.OTHER_PROFILE}/$username")
                    }
                )
                2 -> PlannerScreen(
                    onDetailClick = { travelName ->
                        parentNavController.navigate("${Routes.DETAIL}/$travelName")
                    }
                )
                3 -> ProfileScreen(
                    onDetailClick = { travelName ->
                        parentNavController.navigate("${Routes.DETAIL}/$travelName")
                    },
                    onUserClick = { username ->
                        parentNavController.navigate("${Routes.OTHER_PROFILE}/$username")
                    }
                )
            }
        }
    }
}
