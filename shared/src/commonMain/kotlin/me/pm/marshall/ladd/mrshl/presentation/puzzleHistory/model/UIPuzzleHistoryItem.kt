package me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model

import me.pm.marshall.ladd.mrshl.presentation.core.TileState

data class UIPuzzleHistoryItem(
    val id: Int,
    val guessList: List<TileState> = List(30) { TileState.UnsubmittedGuess(null) },
    val puzzleDateString: String,
    val completedDateString: String?,
)
