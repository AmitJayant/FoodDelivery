package com.amitjayant.fooddelivery.presentation.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = orange500,
    secondary = orange500,
    tertiary = orange500,
    background = Color(0xFFFFFDFB),
    surface = Color(0xFFFFFDFB),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1F1D1B),
    onSurface = Color(0xFF1F1D1B)
)

@Composable
fun FoodDeliveryTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}