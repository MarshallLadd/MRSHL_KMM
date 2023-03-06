package me.pm.marshall.ladd.mrshl.presentation.puzzleScreen

data class Puzzle(
    val id: Long,
    val answer: String,
    val guessList: List<String>,
    val puzzleDateString: String,
    val completedDateString: String?,
)
