package com.example.praktam2_2417051012.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HeroBanner() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ) {

        Box(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            Color(0xFF7B61FF),
                            Color(0xFFFFD54F)
                        )
                    )
                )
                .padding(24.dp)
        ) {

            Column {

                Text(
                    text = "Explore Indonesia",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Discover amazing destinations across the archipelago",
                    color = Color.White
                )
            }
        }
    }
}