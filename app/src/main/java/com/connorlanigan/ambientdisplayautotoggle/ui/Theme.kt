package com.connorlanigan.ambientdisplayautotoggle.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun AmbientDisplayAutoToggleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = remember(darkTheme) {
        if (darkTheme) {
            darkColors()
        } else {
            lightColors()
        }
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}