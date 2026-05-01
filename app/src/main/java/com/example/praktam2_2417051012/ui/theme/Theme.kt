package com.example.praktam2_2417051012.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val TravelColorScheme = lightColorScheme(
    primary = YellowPrimary,
    secondary = YellowSecondary,
    background = YellowBackground,
    surface = WhiteSurface,
    onPrimary = OnPrimaryColor,
    onSurface = OnSurfaceColor
)

@Composable
fun Praktam2_2417051012Theme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = TravelColorScheme,
        typography = AppTypography,
        content = content
    )
}