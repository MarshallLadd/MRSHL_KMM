package me.pm.marshall.ladd.mrshl.android.presentation.puzzleHistory

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowDownward
import androidx.compose.material.icons.sharp.ArrowUpward
import androidx.compose.material.icons.sharp.Leaderboard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.chipShape
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.goodLetterGoodPlaceBackground
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.unsubmittedGuessBorder
import me.pm.marshall.ladd.mrshl.android.presentation.puzzleHistory.composables.PuzzleHistoryItem
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.ListFilterOption
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.ListSortDirection
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.PuzzleHistoryEvent
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.PuzzleHistoryState

@OptIn(ExperimentalMaterialApi::class)
object PuzzleHistoryScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: AndroidPuzzleHistoryViewModel = getViewModel<AndroidPuzzleHistoryViewModel>()
        val state: PuzzleHistoryState by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val scrollState = rememberLazyGridState()
        Scaffold(
            bottomBar = {
                val chipModifier = Modifier.padding(horizontal = 2.dp)
                val chipColors = ChipDefaults.filterChipColors(
                    selectedBackgroundColor = MaterialTheme.colors.secondaryVariant,
                    selectedContentColor = MaterialTheme.colors.onSecondary,
                )
                val switchColors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colors.goodLetterGoodPlaceBackground,
                    checkedTrackColor = MaterialTheme.colors.goodLetterGoodPlaceBackground
                )
                BottomAppBar(
                    contentPadding = WindowInsets.navigationBars.asPaddingValues()
                ) {
                    Icon(
                        modifier = Modifier.padding(start = 8.dp),
                        imageVector = Icons.Sharp.ArrowUpward,
                        contentDescription = null
                    )
                    Switch(
                        checked = state.listSortDirection == ListSortDirection.DESCENDING,
                        onCheckedChange = {
                            viewModel.onEvent(PuzzleHistoryEvent.FlipListDirection)
                        },
                        colors = switchColors
                    )
                    Icon(
                        imageVector = Icons.Sharp.ArrowDownward,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    FilterChip(
                        modifier = chipModifier,
                        onClick = {
                            viewModel.onEvent(PuzzleHistoryEvent.UpdateListFilter(ListFilterOption.ALL))
                        },
                        leadingIcon = null,
                        content = {
                            Text(text = " All ")
                        },
                        selected = state.listFilterOption == ListFilterOption.ALL,
                        shape = MaterialTheme.shapes.chipShape,
                        colors = chipColors
                    )
                    FilterChip(
                        modifier = chipModifier,
                        onClick = {
                            viewModel.onEvent(PuzzleHistoryEvent.UpdateListFilter(ListFilterOption.INCOMPLETE))
                        },
                        leadingIcon = null,
                        content = {
                            Text(text = " New ")
                        },
                        selected = state.listFilterOption == ListFilterOption.INCOMPLETE,
                        shape = MaterialTheme.shapes.chipShape,
                        colors = chipColors
                    )
                    FilterChip(
                        modifier = chipModifier,
                        onClick = {
                            viewModel.onEvent(PuzzleHistoryEvent.UpdateListFilter(ListFilterOption.COMPLETE))
                        },
                        leadingIcon = null,
                        content = {
                            Text(text = " Played ")
                        },
                        selected = state.listFilterOption == ListFilterOption.COMPLETE,
                        shape = MaterialTheme.shapes.chipShape,
                        colors = chipColors
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.onEvent(PuzzleHistoryEvent.FlipListDirection)
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Sharp.Leaderboard,
                            contentDescription = "Show my stats"
                        )
                    },
                    shape = MaterialTheme.shapes.small
                )
            },
            isFloatingActionButtonDocked = true
        ) {
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
                items(
                    if (state.listSortDirection == ListSortDirection.DESCENDING) {
                        state.puzzleHistoryList
                    } else {
                        state.puzzleHistoryList.reversed()
                    }
                ) { uiPuzzleHistoryItem ->
                    PuzzleHistoryItem(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        borderStroke = BorderStroke(
                            width = 2.dp,
                            color = MaterialTheme.colors.unsubmittedGuessBorder
                        ),
                        backgroundColor = MaterialTheme.colors.surface,
                        textColor = MaterialTheme.colors.onSurface,
                        puzzleHistory = uiPuzzleHistoryItem,
                        onClick = {

                        }
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
