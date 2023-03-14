package me.pm.marshall.ladd.mrshl.android.presentation.puzzleHistory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import me.pm.marshall.ladd.mrshl.android.presentation.loadingScreen.AndroidLoadingScreenViewModel
import me.pm.marshall.ladd.mrshl.presentation.loadingScreen.model.LoadingScreenState
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.PuzzleHistoryState


object PuzzleHistoryScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: AndroidPuzzleHistoryViewModel = getViewModel<AndroidPuzzleHistoryViewModel>()
        val state: PuzzleHistoryState by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val scrollState = rememberLazyListState()
        Scaffold() {
            Box(Modifier.padding(it)) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = scrollState
                ) {
                    item {
                        Spacer(modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues()))
                    }
                    items(state.puzzleHistoryList) {uiPuzzleHistoryItem ->
                        Text(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            text = uiPuzzleHistoryItem.puzzleDateString
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()))
                    }
                }
            }
        }
    }
}