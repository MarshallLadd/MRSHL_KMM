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
            tileState02 = boardState.getOrElse(1) { TileState.UnsubmittedGuess(null) },
            tileState03 = boardState.getOrElse(2) { TileState.UnsubmittedGuess(null) },
            tileState04 = boardState.getOrElse(3) { TileState.UnsubmittedGuess(null) },
            tileState05 = boardState.getOrElse(4) { TileState.UnsubmittedGuess(null) },
        )
        PuzzleRow(
            modifier = puzzleRowModifier,
            tileState01 = boardState.getOrElse(5) { TileState.UnsubmittedGuess(null) },
            tileState02 = boardState.getOrElse(6) { TileState.UnsubmittedGuess(null) },
            tileState03 = boardState.getOrElse(7) { TileState.UnsubmittedGuess(null) },
            tileState04 = boardState.getOrElse(8) { TileState.UnsubmittedGuess(null) },
            tileState05 = boardState.getOrElse(9) { TileState.UnsubmittedGuess(null) },
        )
        PuzzleRow(
            modifier = puzzleRowModifier,
            tileState01 = boardState.getOrElse(10) { TileState.UnsubmittedGuess(null) },
            tileState02 = boardState.getOrElse(11) { TileState.UnsubmittedGuess(null) },
            tileState03 = boardState.getOrElse(12) { TileState.UnsubmittedGuess(null) },
            tileState04 = boardState.getOrElse(13) { TileState.UnsubmittedGuess(null) },
            tileState05 = boardState.getOrElse(14) { TileState.UnsubmittedGuess(null) },
        )
        PuzzleRow(
            modifier = puzzleRowModifier,
            tileState01 = boardState.getOrElse(15) { TileState.UnsubmittedGuess(null) },
            tileState02 = boardState.getOrElse(16) { TileState.UnsubmittedGuess(null) },
            tileState03 = boardState.getOrElse(17) { TileState.UnsubmittedGuess(null) },
            tileState04 = boardState.getOrElse(18) { TileState.UnsubmittedGuess(null) },
            tileState05 = boardState.getOrElse(19) { TileState.UnsubmittedGuess(null) },
        )
        PuzzleRow(
            modifier = puzzleRowModifier,
            tileState01 = boardState.getOrElse(20) { TileState.UnsubmittedGuess(null) },
            tileState02 = boardState.getOrElse(21) { TileState.UnsubmittedGuess(null) },
            tileState03 = boardState.getOrElse(22) { TileState.UnsubmittedGuess(null) },
            tileState04 = boardState.getOrElse(23) { TileState.UnsubmittedGuess(null) },
            tileState05 = boardState.getOrElse(24) { TileState.UnsubmittedGuess(null) },
        )
        PuzzleRow(
            modifier = puzzleRowModifier,
            tileState01 = boardState.getOrElse(25) { TileState.UnsubmittedGuess(null) },
            tileState02 = boardState.getOrElse(26) { TileState.UnsubmittedGuess(null) },
            tileState03 = boardState.getOrElse(27) { TileState.UnsubmittedGuess(null) },
            tileState04 = boardState.getOrElse(28) { TileState.UnsubmittedGuess(null) },
            tileState05 = boardState.getOrElse(29) { TileState.UnsubmittedGuess(null) },
        )
    }
}
