package me.pm.marshall.ladd.mrshl.presentation.playPuzzle

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.pm.marshall.ladd.mrshl.core.Result
import me.pm.marshall.ladd.mrshl.core.constants.GameConstants
import me.pm.marshall.ladd.mrshl.core.database.PuzzleDatabaseOperations
import me.pm.marshall.ladd.mrshl.core.flows.MultiplatformStateFlow
import me.pm.marshall.ladd.mrshl.core.flows.toMultiplatformStateFlow
import me.pm.marshall.ladd.mrshl.core.network.NetworkException
import me.pm.marshall.ladd.mrshl.domain.useCases.UpdatePuzzleInCacheUseCase
import me.pm.marshall.ladd.mrshl.domain.useCases.IsValidWordCheckUseCase
import me.pm.marshall.ladd.mrshl.presentation.core.TileState
import me.pm.marshall.ladd.mrshl.presentation.core.toGuessString
import me.pm.marshall.ladd.mrshl.presentation.core.toTileStateString
import me.pm.marshall.ladd.mrshl.presentation.playPuzzle.model.PlayPuzzleEvent
import me.pm.marshall.ladd.mrshl.presentation.playPuzzle.model.PlayPuzzleState

class PlayPuzzleViewModel(
    private val puzzleId: Long,
    private val isValidWordCheckUseCase: IsValidWordCheckUseCase,
    private val updatePuzzleInCacheUseCase: UpdatePuzzleInCacheUseCase,
    private val databaseOperations: PuzzleDatabaseOperations,
    private val coroutineScope: CoroutineScope?,
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(
        value = PlayPuzzleState(
            puzzleId = puzzleId,
            tileState = emptyList(),
        ),
    )
    val state: MultiplatformStateFlow<PlayPuzzleState> =
        combine(
            _state,
            databaseOperations
                .getPuzzleByIdAsFlow(puzzleId),
        ) { state, puzzle ->
            if (state.tileState != puzzle.guessList) {
                state.copy(
                    tileState = puzzle.guessList,
                    numberOfGuesses = puzzle.numberOfGuesses.toInt(),
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
                    tileState = emptyList(),
                ),
            )
            .toMultiplatformStateFlow()

    fun onEvent(event: PlayPuzzleEvent) {
        when (event) {
            is PlayPuzzleEvent.FailedToValidateGuess -> {
            }

            PlayPuzzleEvent.KeyboardDeleteClicked -> {
                viewModelScope.launch {
                    val mutableTileState = state.value.tileState.toMutableList()
                    if (
                        state.value.numberOfGuesses < GameConstants.MAX_GUESSES &&
                        mutableTileState.size > 0 &&
                        mutableTileState.lastIndex < (state.value.numberOfGuesses + 1) * GameConstants.WORD_LENGTH &&
                        mutableTileState.lastIndex >= (state.value.numberOfGuesses) * GameConstants.WORD_LENGTH
                    ) {
                        mutableTileState.removeLastOrNull()
                        val puzzleEntity = databaseOperations.getPuzzleById(puzzleId).copy(
                            guessString = mutableTileState.toGuessString(),
                            tileStatusString = mutableTileState.toTileStateString(),
                        )
                        updatePuzzleInCacheUseCase.execute(
                            puzzleEntity,
                        )
                    }
                }
            }

            PlayPuzzleEvent.KeyboardEnterClicked -> {
                viewModelScope.launch {
                    if (
                        state.value.numberOfGuesses < GameConstants.MAX_GUESSES &&
                        state.value.tileState.isNotEmpty() &&
                        state.value.tileState.size == (state.value.numberOfGuesses + 1) * GameConstants.WORD_LENGTH &&
                        state.value.tileState
                            .take(GameConstants.WORD_LENGTH)
                            .all { it is TileState.UnsubmittedGuess }
                    ) {
                        val guessWord: String = state.value.tileState
                            .takeLast(GameConstants.WORD_LENGTH)
                            .joinToString(separator = "") {
                                it.letter.toString()
                            }
                        when (val result = isValidWordCheckUseCase.execute(guessWord)) {
                            is Result.Error -> {
                                onEvent(
                                    PlayPuzzleEvent.FailedToValidateGuess(
                                        (result.throwable as NetworkException).error,
                                    ),
                                )
                            }

                            is Result.Success -> {
                                onEvent(PlayPuzzleEvent.ReceivedGuessValidationResult(isValidWord = result.data))
                            }
                        }
                    }
                }
            }

            is PlayPuzzleEvent.KeyboardLetterClicked -> {
                viewModelScope.launch {
                    val mutableTileState = state.value.tileState.toMutableList()
                    val numberOfGuesses = state.value.numberOfGuesses
                    if (
                        numberOfGuesses < GameConstants.MAX_GUESSES &&
                        mutableTileState.size / GameConstants.WORD_LENGTH == numberOfGuesses &&
                        mutableTileState.size < GameConstants.MAX_GUESS_STRING_LENGTH
                    ) {
                        mutableTileState.add(TileState.UnsubmittedGuess(event.letter))
                        val puzzleEntity = databaseOperations.getPuzzleById(puzzleId).copy(
                            guessString = mutableTileState.toGuessString(),
                            tileStatusString = mutableTileState.toTileStateString(),
                        )
                        updatePuzzleInCacheUseCase.execute(
                            puzzleEntity,
                        )
                    }
                }
            }

            is PlayPuzzleEvent.ReceivedGuessValidationResult -> {
                if (event.isValidWord) {
                    viewModelScope.launch {
                        val originalPuzzleEntity = databaseOperations.getPuzzleById(puzzleId)
                        updatePuzzleInCacheUseCase
                            .execute(
                                originalPuzzleEntity
                                    .copy(numberOfGuesses = originalPuzzleEntity.numberOfGuesses + 1),
                            )
                    }
                } else {
                }
            }
        }
    }
}
