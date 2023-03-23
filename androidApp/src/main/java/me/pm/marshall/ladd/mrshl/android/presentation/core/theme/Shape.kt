package me.pm.marshall.ladd.mrshl.android.presentation.core.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    small = RoundedCornerShape(0.dp),
)

@get:Composable
val Shapes.chipShape: CornerBasedShape
    get() = RoundedCornerShape(100)
