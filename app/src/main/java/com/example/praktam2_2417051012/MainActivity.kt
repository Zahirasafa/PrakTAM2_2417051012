package com.example.praktam2_2417051012

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.praktam2_2417051012.navigation.AppNavigation
import com.example.praktam2_2417051012.ui.theme.Praktam2_2417051012Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Praktam2_2417051012Theme {

                val navController = rememberNavController()

                AppNavigation(
                    navController = navController
                )
            }
        }
    }
}