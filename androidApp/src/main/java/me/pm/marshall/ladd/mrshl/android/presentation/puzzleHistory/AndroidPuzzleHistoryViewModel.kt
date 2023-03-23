package me.pm.marshall.ladd.mrshl.android.presentation.puzzleHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import me.pm.marshall.ladd.mrshl.core.database.PuzzleDatabaseOperations
import me.pm.marshall.ladd.mrshl.core.flows.MultiplatformStateFlow
import me.pm.marshall.ladd.mrshl.domain.useCases.CachePuzzlesFromRemote
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.PuzzleHistoryViewModel
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.PuzzleHistoryEvent
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.PuzzleHistoryState
import javax.inject.Inject

@HiltViewModel
class AndroidPuzzleHistoryViewModel @Inject constructor(
    private val databaseOperations: PuzzleDatabaseOperations,
    private val cachePuzzles: CachePuzzlesFromRemote
) : ViewModel() {

    private val viewModel by lazy {
        PuzzleHistoryViewModel(databaseOperations, cachePuzzles, viewModelScope)
    }

    val state: MultiplatformStateFlow<PuzzleHistoryState> = viewModel.state

    fun onEvent(event: PuzzleHistoryEvent) {
        viewModel.onEvent(event)
    }
}
