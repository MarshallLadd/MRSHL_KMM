package me.pm.marshall.ladd.mrshl.presentation.core

/**
 * Puzzle class used to show a puzzles state on the play screen.
 *
 * @author Marshall Ladd
 */
data class PuzzleForPlay(
    val id: Long,
    val answer: String,
    val guessList: List<TileState>,
    val numberOfGuesses: Long,
    val puzzleDateString: String,
    val completedDateString: String?,
)
