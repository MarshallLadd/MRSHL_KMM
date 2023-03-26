package me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model

import me.pm.marshall.ladd.mrshl.presentation.core.TileState

data class UIPuzzleHistoryItem(
    val id: Int,
    val tileStateList: List<TileState>,
    val puzzleDateString: String,
    val completedDateString: String?,
)
