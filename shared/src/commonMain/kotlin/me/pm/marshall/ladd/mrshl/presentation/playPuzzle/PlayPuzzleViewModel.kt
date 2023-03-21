package me.pm.marshall.ladd.mrshl.presentation.playPuzzle

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import me.pm.marshall.ladd.mrshl.domain.useCases.UpdatePuzzleInCache
import me.pm.marshall.ladd.mrshl.domain.useCases.ValidateGuess
import me.pm.marshall.ladd.mrshl.presentation.core.PuzzleForPlay
import me.pm.marshall.ladd.mrshl.presentation.playPuzzle.model.PlayPuzzleState

class PlayPuzzleViewModel(
    private val puzzleId: Long,
    private val validateGuess: ValidateGuess,
    private val updatePuzzleInCache: UpdatePuzzleInCache,
    private val coroutineScope: CoroutineScope
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(
        value = PlayPuzzleState(
            puzzle = PuzzleForPlay(
                id = puzzleId,
                answer = "MRSHL",
                guessList = emptyList(),
                puzzleDateString = "",
                completedDateString = null
            )
        )
    )
}
