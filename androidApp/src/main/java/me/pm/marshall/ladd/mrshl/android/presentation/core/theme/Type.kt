package me.pm.marshall.ladd.mrshl.android.presentation.core.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.pm.marshall.ladd.mrshl.android.R

val Rubik = FontFamily(
    Font(resId = R.font.rubik_regular),
    Font(resId = R.font.rubik_bold, FontWeight.Bold),
    Font(resId = R.font.rubik_light, FontWeight.Light),
    Font(resId = R.font.rubik_semibold, FontWeight.SemiBold),
)

// Set of Material typography styles to start with
val typography = Typography(
    h1 = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Light,
        fontSize = 96.sp,
        letterSpacing = (-1.5).sp,
    ),
    h2 = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Light,
        fontSize = 59.sp,
        letterSpacing = (-0.5).sp,
    ),
    h3 = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 47.sp,
        letterSpacing = (0).sp,
    ),
    h4 = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 33.sp,
        letterSpacing = (0.25).sp,
    ),
    h5 = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        letterSpacing = (0).sp,
    ),
    h6 = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        letterSpacing = (0.30).sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = (0.15).sp,
    ),
    subtitle2 = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        letterSpacing = (0.1).sp,
    ),
    body1 = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = (0.5).sp,
    ),
    body2 = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = (0.25).sp,
    ),
    button = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        letterSpacing = (1.25).sp,
    ),
    caption = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = (0.4).sp,
    ),
    overline = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = (1.5).sp,
    ),
)

@get:Composable
val Typography.guessLetter: TextStyle
    get() = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        letterSpacing = (0).sp,
    )
