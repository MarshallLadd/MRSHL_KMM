package me.pm.marshall.ladd.mrshl.presentation.core

data class PuzzleForPlay(
    val id: Long,
    val answer: String,
    val guessList: List<TileState>,
    val puzzleDateString: String,
    val completedDateString: String?,
)
