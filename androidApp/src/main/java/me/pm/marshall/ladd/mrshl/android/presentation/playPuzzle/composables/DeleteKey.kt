package me.pm.marshall.ladd.mrshl.android.presentation.playPuzzle.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.keyboardLetter

@Composable
fun DeleteKey(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.primaryVariant,
        contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primaryVariant),

    ) {
        Text(
            modifier = Modifier
                .padding(
                    horizontal = 6.dp,
                    vertical = 12.dp,
                ),
            text = "Del",
            style = MaterialTheme.typography.keyboardLetter,
            textAlign = TextAlign.Center,
        )
    }
}
