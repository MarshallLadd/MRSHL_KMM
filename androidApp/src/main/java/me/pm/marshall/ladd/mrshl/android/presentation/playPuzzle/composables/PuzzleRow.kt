package me.pm.marshall.ladd.mrshl.android.presentation.playPuzzle.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.pm.marshall.ladd.mrshl.android.presentation.core.composables.PreviewDevices
import me.pm.marshall.ladd.mrshl.android.presentation.core.composables.guessTile.GuessTile
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.MrshlBaseComposable
import me.pm.marshall.ladd.mrshl.presentation.core.TileState

@Composable
fun PuzzleRow(
    modifier: Modifier = Modifier,
    tileState01: TileState,
    tileState02: TileState,
    tileState03: TileState,
    tileState04: TileState,
    tileState05: TileState,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        val tileModifier = Modifier.padding(2.dp)
        GuessTile(
            modifier = tileModifier,
            tileState = tileState01,
        )
        GuessTile(
            modifier = tileModifier,
            tileState = tileState02,
        )
        GuessTile(
            modifier = tileModifier,
            tileState = tileState03,
        )
        GuessTile(
            modifier = tileModifier,
            tileState = tileState04,
        )
        GuessTile(
            modifier = tileModifier,
            tileState = tileState05,
        )
    }
}

@Composable
@PreviewDevices
fun PuzzleRowPreview() {
    MrshlBaseComposable() {
        val puzzleRowModifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 4.dp)
        PuzzleRow(
            modifier = puzzleRowModifier,
            tileState01 = TileState.UnsubmittedGuess(null),
            tileState02 = TileState.GoodGuess('L'),
            tileState03 = TileState.WrongLocationGuess('A'),
            tileState04 = TileState.BadGuess('D'),
            tileState05 = TileState.UnsubmittedGuess('D'),
        )
    }
}
