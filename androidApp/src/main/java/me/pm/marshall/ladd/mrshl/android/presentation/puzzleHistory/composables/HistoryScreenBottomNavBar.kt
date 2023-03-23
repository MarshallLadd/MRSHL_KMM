package me.pm.marshall.ladd.mrshl.android.presentation.puzzleHistory.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowDownward
import androidx.compose.material.icons.sharp.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.chipShape
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.goodLetterGoodPlaceBackground
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.ListFilterOption
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.ListSortDirection
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.PuzzleHistoryEvent
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.PuzzleHistoryState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HistoryScreenBottomNavBar(
    state: PuzzleHistoryState,
    onEvent: (PuzzleHistoryEvent) -> Unit,
) {
    val chipModifier = Modifier.padding(horizontal = 2.dp)
    val chipColors = ChipDefaults.filterChipColors(
        selectedBackgroundColor = MaterialTheme.colors.secondaryVariant,
        selectedContentColor = MaterialTheme.colors.onSecondary,
    )
    val switchColors = SwitchDefaults.colors(
        checkedThumbColor = MaterialTheme.colors.goodLetterGoodPlaceBackground,
        checkedTrackColor = MaterialTheme.colors.goodLetterGoodPlaceBackground,
    )
    BottomAppBar(
        contentPadding = WindowInsets.navigationBars.asPaddingValues(),
    ) {
        Icon(
            modifier = Modifier.padding(start = 8.dp),
            imageVector = Icons.Sharp.ArrowUpward,
            contentDescription = null,
        )
        Switch(
            checked = state.listSortDirection == ListSortDirection.DESCENDING,
            onCheckedChange = {
                onEvent(PuzzleHistoryEvent.FlipListDirection)
            },
            colors = switchColors,
        )
        Icon(
            imageVector = Icons.Sharp.ArrowDownward,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(8.dp))
        FilterChip(
            modifier = chipModifier,
            onClick = {
                onEvent(PuzzleHistoryEvent.UpdateListFilter(ListFilterOption.ALL))
            },
            leadingIcon = null,
            content = {
                Text(text = " All ")
            },
            selected = state.listFilterOption == ListFilterOption.ALL,
            shape = MaterialTheme.shapes.chipShape,
            colors = chipColors,
        )
        FilterChip(
            modifier = chipModifier,
            onClick = {
                onEvent(PuzzleHistoryEvent.UpdateListFilter(ListFilterOption.INCOMPLETE))
            },
            leadingIcon = null,
            content = {
                Text(text = " New ")
            },
            selected = state.listFilterOption == ListFilterOption.INCOMPLETE,
            shape = MaterialTheme.shapes.chipShape,
            colors = chipColors,
        )
        FilterChip(
            modifier = chipModifier,
            onClick = {
                onEvent(PuzzleHistoryEvent.UpdateListFilter(ListFilterOption.COMPLETE))
            },
            leadingIcon = null,
            content = {
                Text(text = " Played ")
            },
            selected = state.listFilterOption == ListFilterOption.COMPLETE,
            shape = MaterialTheme.shapes.chipShape,
            colors = chipColors,
        )
    }
}
