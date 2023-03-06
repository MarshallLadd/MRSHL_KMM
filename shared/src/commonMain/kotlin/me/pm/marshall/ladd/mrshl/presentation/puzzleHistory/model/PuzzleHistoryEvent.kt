package me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model

sealed class PuzzleHistoryEvent {
    data class ChoosePuzzle(val puzzleId: Long) : PuzzleHistoryEvent()
    object ForceUpdateFromRemote : PuzzleHistoryEvent()
    object ScrollToTop : PuzzleHistoryEvent()
    data class JumpToPuzzle(val puzzleId: Long) : PuzzleHistoryEvent()
    object ErrorSeen : PuzzleHistoryEvent()
}
