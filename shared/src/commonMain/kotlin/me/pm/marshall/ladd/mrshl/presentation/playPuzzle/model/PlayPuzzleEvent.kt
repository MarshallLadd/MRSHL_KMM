package me.pm.marshall.ladd.mrshl.presentation.playPuzzle.model

import me.pm.marshall.ladd.mrshl.core.network.NetworkError

sealed class PlayPuzzleEvent {
    data class KeyboardLetterClicked(val letter: Char) : PlayPuzzleEvent()
    object KeyboardDeleteClicked : PlayPuzzleEvent()
    object KeyboardEnterClicked : PlayPuzzleEvent()
    data class FailedToValidateGuess(val error: NetworkError) : PlayPuzzleEvent()
    data class ReceivedGuessValidationResult(val isValidWord: Boolean) : PlayPuzzleEvent()
}
