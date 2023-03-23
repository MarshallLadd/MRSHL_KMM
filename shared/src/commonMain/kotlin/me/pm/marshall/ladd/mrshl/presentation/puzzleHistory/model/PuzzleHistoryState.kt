package me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model

import me.pm.marshall.ladd.mrshl.core.network.NetworkError

data class PuzzleHistoryState(
    val puzzleHistoryList: List<UIPuzzleHistoryItem> = emptyList(),
    val selectedPuzzleId: Int? = null,
    val currentStreak: Int = 0,
    val error: NetworkError? = null,
    val isRefreshing: Boolean = false,
    val listSortDirection: ListSortDirection = ListSortDirection.DESCENDING,
    val listFilterOption: ListFilterOption = ListFilterOption.ALL
)

enum class ListSortDirection {
    ASCENDING,
    DESCENDING
}

enum class ListFilterOption {
    ALL,
    COMPLETE,
    INCOMPLETE
}
