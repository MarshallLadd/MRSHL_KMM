package me.pm.marshall.ladd.mrshl.presentation.playPuzzle

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.pm.marshall.ladd.mrshl.core.Result
import me.pm.marshall.ladd.mrshl.core.database.PuzzleDatabaseOperations
import me.pm.marshall.ladd.mrshl.core.flows.MultiplatformStateFlow
import me.pm.marshall.ladd.mrshl.core.flows.toMultiplatformStateFlow
import me.pm.marshall.ladd.mrshl.domain.useCases.UpdatePuzzleInCache
import me.pm.marshall.ladd.mrshl.domain.useCases.ValidateGuess
import me.pm.marshall.ladd.mrshl.presentation.core.TileState
import me.pm.marshall.ladd.mrshl.presentation.core.toGuessString
import me.pm.marshall.ladd.mrshl.presentation.core.toTileStateString
import me.pm.marshall.ladd.mrshl.presentation.playPuzzle.model.PlayPuzzleEvent
import me.pm.marshall.ladd.mrshl.presentation.playPuzzle.model.PlayPuzzleState

class PlayPuzzleViewModel(
    private val puzzleId: Long,
    private val validateGuess: ValidateGuess,
    private val updatePuzzleInCache: UpdatePuzzleInCache,
    private val databaseOperations: PuzzleDatabaseOperations,
    private val coroutineScope: CoroutineScope?,
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(
        value = PlayPuzzleState(
            puzzleId = puzzleId,
            tileState = emptyList()
        ),
    )
    val state: MultiplatformStateFlow<PlayPuzzleState> =
        combine(
            _state,
            databaseOperations
                .getPuzzleByIdAsFlow(puzzleId)
        ) { state, puzzle ->
            if (state.tileState != puzzle.guessList) {
                state.copy(
                    tileState = puzzle.guessList
                )
            } else {
                state
            }
        }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                PlayPuzzleState(
                    puzzleId = puzzleId,
                    tileState = emptyList()
                )
            )
            .toMultiplatformStateFlow()

    fun onEvent(event: PlayPuzzleEvent) {
        when (event) {
            is PlayPuzzleEvent.FailedToValidateGuess -> TODO()
            PlayPuzzleEvent.KeyboardDeleteClicked -> {
                viewModelScope.launch {
                    val mutableTileState = state.value.tileState.toMutableList()
                    if (mutableTileState.size > 0) {
                        mutableTileState.removeLastOrNull()
                        val puzzleEntity = databaseOperations.getPuzzleById(puzzleId).copy(
                            guessString = mutableTileState.toGuessString(),
                            tileStatusString = mutableTileState.toTileStateString()
                        )
                        updatePuzzleInCache.execute(
                            puzzleEntity
                        )
                    }
                }
            }

            PlayPuzzleEvent.KeyboardEnterClicked -> {
                viewModelScope.launch {
                    if (
                        state.value.tileState.isNotEmpty() &&
                        state.value.tileState.size % 5 == 0 &&
                        !(state.value.tileState
                            .takeLast(5)
                            .any { it !is TileState.GoodGuess })
                    ) {
                        val guessWord: String = state.value.tileState
                            .takeLast(5)
                            .joinToString(separator = "") {
                                it.letter.toString()
                            }
                        when (val result = validateGuess.execute(guessWord)) {
                            is Result.Error -> {

                            }

                            is Result.Success -> {
                                _state.update {
                                    it.copy(numberOfGuesses = it.numberOfGuesses + 1)
                                }
                            }
                        }
                    }
                }
            }

            is PlayPuzzleEvent.KeyboardLetterClicked -> {
                viewModelScope.launch {
                    val mutableTileState = state.value.tileState.toMutableList()
                    if (mutableTileState.size < 30) {
                        mutableTileState.add(TileState.UnsubmittedGuess(event.letter))
                        val puzzleEntity = databaseOperations.getPuzzleById(puzzleId).copy(
                            guessString = mutableTileState.toGuessString(),
                            tileStatusString = mutableTileState.toTileStateString()
                        )
                        updatePuzzleInCache.execute(
                            puzzleEntity
                        )
                    }
                }
            }

            is PlayPuzzleEvent.ReceivedGuessValidationResult -> TODO()
        }
    }
}
