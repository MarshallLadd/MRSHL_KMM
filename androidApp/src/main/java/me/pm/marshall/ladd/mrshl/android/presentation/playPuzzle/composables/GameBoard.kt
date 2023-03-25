package me.pm.marshall.ladd.mrshl.android.presentation.playPuzzle.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.pm.marshall.ladd.mrshl.presentation.core.TileState

@Composable
fun GameBoard(
    modifier: Modifier = Modifier,
    boardState: List<TileState>,
) {
    Column(modifier = modifier) {
        val puzzleRowModifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 4.dp)
        PuzzleRow(
            modifier = puzzleRowModifier,
            tileState01 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState02 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState03 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState04 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState05 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
        )
        PuzzleRow(
            modifier = puzzleRowModifier,
            tileState01 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState02 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState03 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState04 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState05 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
        )
        PuzzleRow(
            modifier = puzzleRowModifier,
            tileState01 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState02 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState03 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState04 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState05 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
        )
        PuzzleRow(
            modifier = puzzleRowModifier,
            tileState01 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState02 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState03 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState04 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState05 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
        )
        PuzzleRow(
            modifier = puzzleRowModifier,
            tileState01 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState02 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState03 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState04 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState05 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
        )
        PuzzleRow(
            modifier = puzzleRowModifier,
            tileState01 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState02 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState03 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState04 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
            tileState05 = boardState.getOrElse(0) { TileState.UnsubmittedGuess(null) },
        )
    }
}
