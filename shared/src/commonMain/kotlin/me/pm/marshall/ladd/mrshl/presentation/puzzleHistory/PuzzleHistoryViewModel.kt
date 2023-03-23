package me.pm.marshall.ladd.mrshl.presentation.puzzleHistory

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.pm.marshall.ladd.mrshl.core.database.PuzzleDatabaseOperations
import me.pm.marshall.ladd.mrshl.core.flows.toMultiplatformStateFlow
import me.pm.marshall.ladd.mrshl.core.mappers.toUIPuzzleHistoryEntity
import me.pm.marshall.ladd.mrshl.domain.useCases.CachePuzzlesFromRemote
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.ListSortDirection
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.PuzzleHistoryEvent
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.PuzzleHistoryState

class PuzzleHistoryViewModel(
    private val databaseOperations: PuzzleDatabaseOperations,
    private val cachePuzzles: CachePuzzlesFromRemote,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(value = PuzzleHistoryState())
    val state = combine(_state, databaseOperations.getAllPuzzlesAsFlow()) { state, puzzleList ->
        if (state.puzzleHistoryList != puzzleList) {
            state.copy(
                puzzleHistoryList = puzzleList.map { it.toUIPuzzleHistoryEntity() }
            )
        } else state
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), PuzzleHistoryState())
        .toMultiplatformStateFlow()

    fun onEvent(event: PuzzleHistoryEvent) {
        when (event) {
            is PuzzleHistoryEvent.ChoosePuzzle -> TODO()
            PuzzleHistoryEvent.ErrorSeen -> TODO()
            PuzzleHistoryEvent.ForceUpdateFromRemote -> {
                viewModelScope.launch {
                    _state.update { it.copy(isRefreshing = true) }
                    cachePuzzles.execute()
                    _state.update { it.copy(isRefreshing = false) }
                }
            }

            is PuzzleHistoryEvent.JumpToPuzzle -> TODO()
            PuzzleHistoryEvent.ScrollToTop -> TODO()
            PuzzleHistoryEvent.FlipListDirection -> {
                val newDirection = if (state.value.listSortDirection == ListSortDirection.DESCENDING) {
                    ListSortDirection.ASCENDING
                } else {
                    ListSortDirection.DESCENDING
                }
                _state.update { it.copy(listSortDirection = newDirection) }
            }

            is PuzzleHistoryEvent.UpdateListFilter -> {
                _state.update { it.copy(listFilterOption = event.listFilterOption) }
            }
        }
    }
}
