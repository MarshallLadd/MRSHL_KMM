package me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model

import me.pm.marshall.ladd.mrshl.presentation.core.TileState

data class UIPuzzleHistoryItem(
    val id: Int,
    val guessList: List<TileState> = List(30) {
        if (it < 15) {
            if (it % 3 == 0) TileState.BadGuess('M')
            else if (it % 4 == 0) TileState.WrongLocationGuess('M')
            else TileState.GoodGuess('M')
        } else {
            TileState.UnsubmittedGuess(null)
        }
    },
    val puzzleDateString: String,
    val completedDateString: String?,
)
