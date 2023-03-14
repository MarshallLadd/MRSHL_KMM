package me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model

import me.pm.marshall.ladd.mrshl.core.network.NetworkError

data class PuzzleHistoryState(
    val puzzleHistoryList: List<UIPuzzleHistoryEntity> = emptyList(),
    val selectedPuzzleId: Int? = null,
    val currentStreak: Int = 0,
    val error: NetworkError? = null,
)
