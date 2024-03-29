package me.pm.marshall.ladd.mrshl.android.presentation.playPuzzle.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Keyboard(
    modifier: Modifier,
    onEnterPress: () -> Unit,
    onDeletePress: () -> Unit,
    onLetterPress: (Char) -> Unit,
) {
    val rowModifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)
    val letterKeyModifier = Modifier
        .wrapContentSize()
        .sizeIn(minWidth = 36.dp)
        .padding(horizontal = 3.dp)
    val specialKeyModifier = Modifier
        .wrapContentSize()
        .sizeIn(minWidth = 56.dp)
        .padding(horizontal = 2.dp)
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.large,
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(WindowInsets.navigationBars.asPaddingValues())
                .padding(bottom = 8.dp, top = 16.dp),
        ) {
            Row(
                modifier = rowModifier,
                horizontalArrangement = Arrangement.Center,
            ) {
                "QWERTYUIOP".forEach {
                    LetterKey(
                        modifier = letterKeyModifier,
                        onLetterPress = onLetterPress,
                        letter = it,
                    )
                }
            }
            Row(
                modifier = rowModifier,
                horizontalArrangement = Arrangement.Center,
            ) {
                "ASDFGHJKL".forEach {
                    LetterKey(
                        modifier = letterKeyModifier,
                        onLetterPress = onLetterPress,
                        letter = it,
                    )
                }
            }
            Row(
                modifier = rowModifier,
                horizontalArrangement = Arrangement.Center,
            ) {
                EnterKey(modifier = specialKeyModifier) {
                    onEnterPress()
                }
                "ZXCVBNM".forEach {
                    LetterKey(
                        modifier = letterKeyModifier,
                        onLetterPress = onLetterPress,
                        letter = it,
                    )
                }
                DeleteKey(modifier = specialKeyModifier) {
                    onDeletePress()
                }
            }
        }
    }
}
