package me.pm.marshall.ladd.mrshl.android.presentation.puzzleHistory

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.goodLetterBadPlaceBackground
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.onLetterGuess
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.unsubmittedGuessBorder
import me.pm.marshall.ladd.mrshl.android.presentation.loadingScreen.AndroidLoadingScreenViewModel
import me.pm.marshall.ladd.mrshl.android.presentation.puzzleHistory.composables.PuzzleHistoryItem
import me.pm.marshall.ladd.mrshl.presentation.loadingScreen.model.LoadingScreenState
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.PuzzleHistoryState


object PuzzleHistoryScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: AndroidPuzzleHistoryViewModel = getViewModel<AndroidPuzzleHistoryViewModel>()
        val state: PuzzleHistoryState by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val scrollState = rememberLazyGridState()
        Scaffold() {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                state = scrollState,
                columns = GridCells.Adaptive(minSize = 128.dp)
            ) {
                item(span = {
                    GridItemSpan(maxLineSpan)
                }) {
                    Spacer(modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues()))
                }
                items(state.puzzleHistoryList) { uiPuzzleHistoryItem ->
                    PuzzleHistoryItem(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        borderStroke = BorderStroke(
                            width = 2.dp,
                            color = MaterialTheme.colors.unsubmittedGuessBorder
                        ),
                        backgroundColor = MaterialTheme.colors.surface,
                        textColor = MaterialTheme.colors.onSurface,
                        puzzleHistory = uiPuzzleHistoryItem
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
