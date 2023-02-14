package com.android.blinxapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    surface = white,
    onSurface = Color.White,
    primary = Color.Black,
    onPrimary = white,
    outline = Color.White,
    onSecondary = Color.White,
)

private val LightColorPalette = lightColorScheme(
    surface = Color.Black,
    onSurface = Color.Black,
    primary = white,
    outline = Color.Black,
    onPrimary = white,
    onSecondary = Color.Black,


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun BlinxAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}