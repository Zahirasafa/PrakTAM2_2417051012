package com.example.praktam2_2417051012.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val TravelioColorScheme = lightColorScheme(
    primary = TravelioYellow,
    secondary = TravelioPurple,
    tertiary = TravelioLightPurple,
    background = TravelioCream,
    surface = TravelioWhite,
    onPrimary = TravelioText,
    onSecondary = TravelioWhite,
    onSurface = TravelioText
)

@Composable
fun Praktam2_2417051012Theme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = TravelioColorScheme,
        typography = AppTypography,
        content = content
    )
}