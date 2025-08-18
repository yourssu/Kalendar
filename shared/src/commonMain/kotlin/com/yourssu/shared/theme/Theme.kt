package com.yourssu.shared.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val LocalKalendarColors = staticCompositionLocalOf<KalendarColors> {
    error("No KalendarColors provided")
}

private val LocalKalendarTypography = staticCompositionLocalOf<KalendarTypography> {
    error("No KalendarTypography provided")
}

object KalendarTheme {
    val colors: KalendarColors
        @Composable
        @ReadOnlyComposable
        get() = LocalKalendarColors.current

    val typography: KalendarTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalKalendarTypography.current
}

@Composable
fun KalendarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val kalendarColors = if (darkTheme) DarkKalendarColors else LightKalendarColors
    val typography = rememberAppTypography()

    CompositionLocalProvider(
        LocalKalendarColors provides kalendarColors,
        LocalKalendarTypography provides typography,
    ) {
        MaterialTheme(
            content = content,
            colors = MaterialTheme.colors.copy(
                background = Color.White
            )
        )
    }
}
