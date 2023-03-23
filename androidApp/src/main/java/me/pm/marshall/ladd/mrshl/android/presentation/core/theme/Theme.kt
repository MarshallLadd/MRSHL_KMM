package me.pm.marshall.ladd.mrshl.android.presentation.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MrshlAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorPalette
    } else {
        lightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@Composable
fun MrshlBaseComposable(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MrshlAppTheme(darkTheme = darkTheme) {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = MaterialTheme.colors.isLight
        val navigationBarColor = Color.Transparent
        val statusBarColor = Color.Transparent

        DisposableEffect(systemUiController, useDarkIcons) {
            systemUiController.setNavigationBarColor(color = navigationBarColor, darkIcons = useDarkIcons)
            systemUiController.setStatusBarColor(color = statusBarColor, darkIcons = useDarkIcons)
            onDispose { }
        }
        Box {
            content()
        }
    }
}
