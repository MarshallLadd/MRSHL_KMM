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
import me.pm.marshall.ladd.mrshl.core.flows.MultiplatformStateFlow
import me.pm.marshall.ladd.mrshl.core.flows.toMultiplatformStateFlow
import me.pm.marshall.ladd.mrshl.core.mappers.toUIPuzzleHistoryEntity
import me.pm.marshall.ladd.mrshl.domain.useCases.CachePuzzlesFromRemoteUseCase
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.ListFilterOption
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.ListSortDirection
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.PuzzleHistoryEvent
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.PuzzleHistoryState

class PuzzleHistoryViewModel(
    private val databaseOperations: PuzzleDatabaseOperations,
    private val cachePuzzles: CachePuzzlesFromRemoteUseCase,
    private val coroutineScope: CoroutineScope?,
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(value = PuzzleHistoryState())
    val state: MultiplatformStateFlow<PuzzleHistoryState> =
        combine(
            _state,
            databaseOperations.getAllPuzzlesAsFlow(),
        ) { state, puzzleList ->
            if (state.puzzleHistoryList != puzzleList) {
                var newList = puzzleList.map { it.toUIPuzzleHistoryEntity() }
                if (state.listSortDirection == ListSortDirection.DESCENDING) {
                    newList = newList.reversed()
                }
                newList = when (state.listFilterOption) {
                    ListFilterOption.ALL -> newList
                    ListFilterOption.COMPLETE -> newList.filter { it.completedDateString != null }
                    ListFilterOption.INCOMPLETE -> newList.filter { it.completedDateString == null }
                }
                state.copy(
                    puzzleHistoryList = newList,
                )
            } else {
                state
            }
        }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), PuzzleHistoryState())
            .toMultiplatformStateFlow()

    fun onEvent(event: PuzzleHistoryEvent) {
        when (event) {
            is PuzzleHistoryEvent.ChoosePuzzle -> {
                _state.update {
                    val requestedPuzzleId: Long = event.puzzleId ?: kotlin.run {
                        databaseOperations.getAllUnplayedPuzzlesAsList().random().id
                    }
                    it.copy(selectedPuzzleId = requestedPuzzleId)
                }
            }

            PuzzleHistoryEvent.ForceUpdateFromRemote -> {
                viewModelScope.launch {
                    _state.update { it.copy(isRefreshing = true) }
                    cachePuzzles.execute()
                    _state.update { it.copy(isRefreshing = false) }
                }
            }

            PuzzleHistoryEvent.FlipListDirection -> {
                val newDirection =
                    if (state.value.listSortDirection == ListSortDirection.DESCENDING) {
                        ListSortDirection.ASCENDING
                    } else {
                        ListSortDirection.DESCENDING
                    }
                _state.update { it.copy(listSortDirection = newDirection) }
            }

            is PuzzleHistoryEvent.UpdateListFilter -> {
                _state.update { it.copy(listFilterOption = event.listFilterOption) }
            }

            PuzzleHistoryEvent.NavigatingToPuzzle -> {
                _state.update {
                    it.copy(selectedPuzzleId = null)
                }
            }
        }
    }
}
