package me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model

data class UIPuzzleHistoryEntity(
    val id: Long,
    val guessList: List<String>,
    val puzzleDateString: String,
    val completedDateString: String?,
)
