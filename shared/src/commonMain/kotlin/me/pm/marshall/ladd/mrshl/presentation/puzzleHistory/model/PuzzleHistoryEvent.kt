package me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model

sealed class PuzzleHistoryEvent {
    data class ChoosePuzzle(val puzzleId: Long?) : PuzzleHistoryEvent()
    object NavigatingToPuzzle : PuzzleHistoryEvent()
    object ForceUpdateFromRemote : PuzzleHistoryEvent()
    object FlipListDirection : PuzzleHistoryEvent()
    data class UpdateListFilter(val listFilterOption: ListFilterOption) : PuzzleHistoryEvent()
}
