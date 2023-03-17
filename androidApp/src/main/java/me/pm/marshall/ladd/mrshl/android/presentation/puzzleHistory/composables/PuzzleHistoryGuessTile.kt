package me.pm.marshall.ladd.mrshl.android.presentation.puzzleHistory.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.pm.marshall.ladd.mrshl.android.presentation.core.composables.PreviewDevices
import me.pm.marshall.ladd.mrshl.android.presentation.core.composables.guessTile.GuessTile
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.MrshlBaseComposable
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.badLetterBackground
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.goodLetterBadPlaceBackground
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.goodLetterGoodPlaceBackground
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.guessLetter
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.onLetterGuess
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.unsubmittedGuessBorder
import me.pm.marshall.ladd.mrshl.presentation.core.TileState


@Composable
fun PuzzleHistoryGuessTile(
    modifier: Modifier = Modifier,
    tileState: TileState
) {
    when (tileState) {
        is TileState.BadGuess -> PuzzleHistoryGuessTile(
            modifier = modifier,
            borderStroke = BorderStroke(
                width = 0.dp,
                color = Color.Transparent
            ),
            backgroundColor = MaterialTheme.colors.badLetterBackground,
            letterColor = MaterialTheme.colors.onLetterGuess
        )

        is TileState.GoodGuess -> PuzzleHistoryGuessTile(
            modifier = modifier,
            borderStroke = BorderStroke(
                width = 0.dp,
                color = Color.Transparent
            ),
            backgroundColor = MaterialTheme.colors.goodLetterGoodPlaceBackground,
            letterColor = MaterialTheme.colors.onLetterGuess
        )

        is TileState.UnsubmittedGuess -> PuzzleHistoryGuessTile(
            modifier = modifier,
            borderStroke = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colors.unsubmittedGuessBorder
            ),
            backgroundColor = MaterialTheme.colors.surface,
            letterColor = MaterialTheme.colors.onSurface
        )

        is TileState.WrongLocationGuess -> PuzzleHistoryGuessTile(
            modifier = modifier,
            borderStroke = BorderStroke(
                width = 0.dp,
                color = Color.Transparent
            ),
            backgroundColor = MaterialTheme.colors.goodLetterBadPlaceBackground,
            letterColor = MaterialTheme.colors.onLetterGuess
        )
    }
}


@Composable
fun PuzzleHistoryGuessTile(
    modifier: Modifier = Modifier,
    borderStroke: BorderStroke,
    backgroundColor: Color,
    letterColor: Color,
    letter: Char? = null
) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .border(borderStroke)
                .size(16.dp)
                .background(color = backgroundColor)
        )
    }
}

@Composable
@PreviewDevices
fun GuessTilePreview() {
    MrshlBaseComposable(darkTheme = false) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = MaterialTheme.colors.background),
            horizontalArrangement = Arrangement.Center
        ) {
            val modifier = Modifier.padding(2.dp)
            PuzzleHistoryGuessTile(
                modifier = modifier,
                tileState = TileState.UnsubmittedGuess('M')
            )
            PuzzleHistoryGuessTile(
                modifier = modifier,
                tileState = TileState.GoodGuess('R')
            )
            PuzzleHistoryGuessTile(
                modifier = modifier,
                tileState = TileState.WrongLocationGuess('S')
            )
            PuzzleHistoryGuessTile(
                modifier = modifier,
                tileState = TileState.BadGuess('H')
            )
            PuzzleHistoryGuessTile(
                modifier = modifier,
                tileState = TileState.UnsubmittedGuess(null)
            )
        }
    }
}