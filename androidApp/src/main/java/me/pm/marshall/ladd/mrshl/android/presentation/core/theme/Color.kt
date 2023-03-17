package me.pm.marshall.ladd.mrshl.android.presentation.core.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import me.pm.marshall.ladd.mrshl.presentation.core.MrshlColors

val lightColorPalette = lightColors(
    primary = Color(MrshlColors.White),
    primaryVariant = Color(MrshlColors.LightKeyboardGrey),
    secondary = Color(MrshlColors.Green),
    secondaryVariant = Color(MrshlColors.Yellow),
    background = Color(MrshlColors.LightBackground),
    surface = Color(MrshlColors.White),
    onPrimary = Color(MrshlColors.Black),
    onSecondary = Color(MrshlColors.White),
    onSurface = Color(MrshlColors.Black),
    onBackground = Color(MrshlColors.Black),
    error = Color(MrshlColors.Error),
    onError = Color(MrshlColors.White),
)

val darkColorPalette = darkColors(
    primary = Color(MrshlColors.DarkBackground),
    primaryVariant = Color(MrshlColors.DarkKeyboardGrey),
    secondary = Color(MrshlColors.Green),
    secondaryVariant = Color(MrshlColors.Yellow),
    background = Color(MrshlColors.DarkBackground),
    surface = Color(MrshlColors.DarkBackground),
    onPrimary = Color(MrshlColors.White),
    onSecondary = Color(MrshlColors.White),
    onSurface = Color(MrshlColors.White),
    onBackground = Color(MrshlColors.White),
    error = Color(MrshlColors.Error),
    onError = Color(MrshlColors.White),
)

@get:Composable
val Colors.goodLetterGoodPlaceBackground: Color
    get() = Color(MrshlColors.Green)

@get:Composable
val Colors.goodLetterBadPlaceBackground: Color
    get() = Color(MrshlColors.Yellow)

@get:Composable
val Colors.badLetterBackground: Color
    get() = if (isLight) Color(MrshlColors.LightBadGuessBackground) else Color(MrshlColors.DarkBadGuessBackground)

@get:Composable
val Colors.onLetterGuess: Color
    get() = Color(MrshlColors.White)

@get:Composable
val Colors.unsubmittedGuessBorder: Color
    get() = if (isLight) Color(MrshlColors.LightBorderEmpty) else Color(MrshlColors.DarkBorderEmpty)
