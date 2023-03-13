package me.pm.marshall.ladd.mrshl.android.presentation.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import me.pm.marshall.ladd.mrshl.android.presentation.loadingScreen.LoadingSplashScreen

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
//        shapes = Shapes,
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
