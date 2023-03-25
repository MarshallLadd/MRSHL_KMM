package me.pm.marshall.ladd.mrshl.android.presentation.playPuzzle.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
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
        .padding(bottom = 4.dp)
    val letterKeyModifier = Modifier
        .wrapContentSize()
        .sizeIn(minWidth = 36.dp)
        .padding(horizontal = 2.dp)
    val specialKeyModifier = Modifier
        .wrapContentSize()
        .sizeIn(minWidth = 56.dp)
        .padding(horizontal = 2.dp)
    Column(modifier = modifier) {
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
