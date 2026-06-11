package com.example.praktam2_2417051012.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.praktam2_2417051012.ui.auth.AuthChoiceScreen
import com.example.praktam2_2417051012.ui.auth.LoginScreen
import com.example.praktam2_2417051012.ui.auth.SignUpScreen
import com.example.praktam2_2417051012.ui.auth.WelcomeScreen
import com.example.praktam2_2417051012.ui.auth.ForgotPasswordScreen
import com.example.praktam2_2417051012.ui.main.MainContainerScreen
import com.example.praktam2_2417051012.ui.detail.DetailScreen
import com.example.praktam2_2417051012.ui.region.RegionScreen
import com.example.praktam2_2417051012.ui.profile.ProfileScreen

@Composable
fun AppNavigation(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Routes.WELCOME
    ) {

        composable(Routes.WELCOME) {
            WelcomeScreen(
                onFinished = {
                    navController.navigate(Routes.AUTH_CHOICE) {
                        popUpTo(Routes.WELCOME) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.AUTH_CHOICE) {
            AuthChoiceScreen(
                onLoginClick = {
                    navController.navigate(Routes.LOGIN)
                },
                onSignUpClick = {
                    navController.navigate(Routes.SIGNUP)
                }
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.MAIN_CONTAINER) {
                        popUpTo(Routes.AUTH_CHOICE) { inclusive = true }
                    }
                },
                onForgotPasswordClick = {
                    navController.navigate(Routes.FORGOT_PASSWORD)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.SIGNUP) {
            SignUpScreen(
                onSignUpSuccess = {
                    navController.navigate(Routes.MAIN_CONTAINER) {
                        popUpTo(Routes.AUTH_CHOICE) { inclusive = true }
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.FORGOT_PASSWORD) {
            ForgotPasswordScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.MAIN_CONTAINER) {
            MainContainerScreen(parentNavController = navController)
        }

        composable(
            route = "${Routes.DETAIL}/{travelName}",
            arguments = listOf(navArgument("travelName") { type = NavType.StringType })
        ) { backStackEntry ->
            val travelName = backStackEntry.arguments?.getString("travelName") ?: ""
            DetailScreen(
                navController = navController,
                travelName = travelName
            )
        }

        composable(
            route = "${Routes.REGION}/{regionName}",
            arguments = listOf(navArgument("regionName") { type = NavType.StringType })
        ) { backStackEntry ->
            val regionName = backStackEntry.arguments?.getString("regionName") ?: ""
            RegionScreen(
                navController = navController,
                regionName = regionName
            )
        }

        composable(
            route = "${Routes.OTHER_PROFILE}/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            ProfileScreen(
                onDetailClick = { travelName ->
                    navController.navigate("${Routes.DETAIL}/$travelName")
                },
                username = username,
                onBackClick = { navController.popBackStack() },
                onUserClick = { otherUser ->
                    navController.navigate("${Routes.OTHER_PROFILE}/$otherUser")
                }
            )
        }
    }
}
