package com.dev.healthreminder.presentation.ui.theming

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF3c7962)
val Secondary = Color(0xFF77bba2)
val Tertiary = Color(0xFFabcfc2)
val LightSurface = Color(0xFFD4FFD9)
val White = Color(0xFFffffff)
val DarkSurface = Color(0xFF292929)
val BlackishGrey = Color(0xFF171616)
val Red = Color(0xFFF62020)

internal val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = White,
    background = LightSurface,
    onBackground = Primary,
    secondary = Secondary,
    onSecondary = Primary,
    tertiary = Tertiary,
    onTertiary = Secondary,
    onError = Red
)

internal val DarkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = BlackishGrey,
    background = DarkSurface,
    onBackground = Primary,
    secondary = Secondary,
    onSecondary = Primary,
    tertiary = Tertiary,
    onTertiary = Secondary,
    onError = Red
)