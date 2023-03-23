package me.pm.marshall.ladd.mrshl.android.presentation.puzzleHistory.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import me.pm.marshall.ladd.mrshl.android.presentation.core.composables.PreviewDevices
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.MrshlBaseComposable
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.unsubmittedGuessBorder
import me.pm.marshall.ladd.mrshl.presentation.core.TileState
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.UIPuzzleHistoryItem

@Composable
fun PuzzleHistoryItem(
    modifier: Modifier = Modifier,
    borderStroke: BorderStroke,
    backgroundColor: Color,
    textColor: Color,
    puzzleHistory: UIPuzzleHistoryItem,
    onClick: (Long) -> Unit,
) {
    Column(
        modifier = modifier
            .border(borderStroke)
            .background(color = backgroundColor)
//            .padding(4.dp)
            .clickable {
                onClick(puzzleHistory.id.toLong())
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = "${puzzleHistory.id}",
            style = MaterialTheme.typography.h6.copy(color = textColor),
            textAlign = TextAlign.Center,
        )
        val tileModifier = Modifier.padding(horizontal = 1.dp)
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(2.dp),
        ) {
            (0..4).forEach {
                PuzzleHistoryGuessTile(
                    modifier = tileModifier,
                    tileState = puzzleHistory.guessList[it],
                )
            }
        }
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(2.dp),
        ) {
            (5..9).forEach {
                PuzzleHistoryGuessTile(
                    modifier = tileModifier,
                    tileState = puzzleHistory.guessList[it],
                )
            }
        }
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(2.dp),
        ) {
            (10..14).forEach {
                PuzzleHistoryGuessTile(
                    modifier = tileModifier,
                    tileState = puzzleHistory.guessList[it],
                )
            }
        }
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(2.dp),
        ) {
            (15..19).forEach {
                PuzzleHistoryGuessTile(
                    modifier = tileModifier,
                    tileState = puzzleHistory.guessList[it],
                )
            }
        }
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(2.dp),
        ) {
            (20..24).forEach {
                PuzzleHistoryGuessTile(
                    modifier = tileModifier,
                    tileState = puzzleHistory.guessList[it],
                )
            }
        }
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(2.dp),
        ) {
            (25..29).forEach {
                PuzzleHistoryGuessTile(
                    modifier = tileModifier,
                    tileState = puzzleHistory.guessList[it],
                )
            }
        }
        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            text = puzzleHistory.puzzleDateString,
            style = MaterialTheme.typography.caption.copy(color = textColor),
            textAlign = TextAlign.Center,
        )
    }
}

@PreviewDevices
@Composable
fun PuzzleHistoryItemPreviews() {
    MrshlBaseComposable {
        PuzzleHistoryItem(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            borderStroke = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colors.unsubmittedGuessBorder,
            ),
            backgroundColor = MaterialTheme.colors.surface,
            textColor = MaterialTheme.colors.onSurface,
            puzzleHistory = UIPuzzleHistoryItem(
                id = 420,
                guessList = List(30) {
                    if (it % 3 == 0) {
                        TileState.BadGuess('L')
                    } else if (it % 4 == 0) {
                        TileState.WrongLocationGuess('L')
                    } else if (it % 7 == 0) {
                        TileState.GoodGuess('L')
                    } else {
                        TileState.UnsubmittedGuess(null)
                    }
                },
                puzzleDateString = "2021-12-18",
                completedDateString = null,
            ),
            onClick = {
            },
        )
    }
}
