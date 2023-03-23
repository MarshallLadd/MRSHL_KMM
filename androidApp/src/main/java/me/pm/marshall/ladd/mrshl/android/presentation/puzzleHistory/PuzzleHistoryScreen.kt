package me.pm.marshall.ladd.mrshl.android.presentation.puzzleHistory

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Leaderboard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.unsubmittedGuessBorder
import me.pm.marshall.ladd.mrshl.android.presentation.playPuzzle.PlayPuzzleScreen
import me.pm.marshall.ladd.mrshl.android.presentation.puzzleHistory.composables.HistoryScreenBottomNavBar
import me.pm.marshall.ladd.mrshl.android.presentation.puzzleHistory.composables.PuzzleHistoryItem
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.ListSortDirection
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.PuzzleHistoryEvent
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.PuzzleHistoryState

@OptIn(ExperimentalMaterialApi::class)
object PuzzleHistoryScreen : AndroidScreen() {
    private var priorSelectedPuzzleId: Long? = null

    @Composable
    override fun Content() {
        val viewModel: AndroidPuzzleHistoryViewModel = getViewModel<AndroidPuzzleHistoryViewModel>()
        val state: PuzzleHistoryState by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val scrollState = rememberLazyGridState()
        LaunchedEffect(key1 = state.selectedPuzzleId) {
            state.selectedPuzzleId?.let {
                navigator push PlayPuzzleScreen(it)
                viewModel.onEvent(PuzzleHistoryEvent.NavigatingToPuzzle)
            }
        }
        Scaffold(
            bottomBar = {
                HistoryScreenBottomNavBar(state = state, onEvent = viewModel::onEvent)
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.onEvent(PuzzleHistoryEvent.FlipListDirection)
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Sharp.Leaderboard,
                            contentDescription = "Show my stats",
                        )
                    },
                    shape = MaterialTheme.shapes.small,
                )
            },
            isFloatingActionButtonDocked = true,
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                state = scrollState,
                columns = GridCells.Adaptive(minSize = 128.dp),
            ) {
                item(span = {
                    GridItemSpan(maxLineSpan)
                }) {
                    Spacer(modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues()))
                }
                items(
                    if (state.listSortDirection == ListSortDirection.DESCENDING) {
                        state.puzzleHistoryList
                    } else {
                        state.puzzleHistoryList.reversed()
                    },
                ) { uiPuzzleHistoryItem ->
                    PuzzleHistoryItem(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        borderStroke = BorderStroke(
                            width = 2.dp,
                            color = MaterialTheme.colors.unsubmittedGuessBorder,
                        ),
                        backgroundColor = MaterialTheme.colors.surface,
                        textColor = MaterialTheme.colors.onSurface,
                        puzzleHistory = uiPuzzleHistoryItem,
                        onClick = {
                            viewModel.onEvent(PuzzleHistoryEvent.ChoosePuzzle(it))
                        },
                    )
                }
                item(span = {
                    GridItemSpan(maxLineSpan)
                }) {
                    Spacer(modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()))
                }
            }
        }
    }
}
