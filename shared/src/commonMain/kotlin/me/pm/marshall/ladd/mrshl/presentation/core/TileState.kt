package me.pm.marshall.ladd.mrshl.presentation.core

/**
 * Represents the possible states a square may be in during play.
 */
sealed class TileState(val letter: Char?) {
    class UnsubmittedGuess(letter: Char?) : TileState(letter)
    class BadGuess(letter: Char?) : TileState(letter)
    class GoodGuess(letter: Char?) : TileState(letter)
    class WrongLocationGuess(letter: Char?) : TileState(letter)
}

fun TileState.toInt(): Int {
    return when (this) {
        is TileState.UnsubmittedGuess -> 0
        is TileState.BadGuess -> 1
        is TileState.WrongLocationGuess -> 2
        is TileState.GoodGuess -> 3
    }
}

fun Int.toTileState(letter: Char?): TileState {
    return when (this) {
        0 -> TileState.UnsubmittedGuess(letter)
        1 -> TileState.BadGuess(letter)
        2 -> TileState.WrongLocationGuess(letter)
        3 -> TileState.GoodGuess(letter)
        // TODO This should throw an error and be handled in the VM and View
        else -> TileState.UnsubmittedGuess(letter)
    }
}

fun List<TileState>.toGuessString(): String {
    var guessString: String = ""
    this.forEach { tileState ->
        tileState
            .letter
            ?.let {
                guessString += it
            }
    }
    return guessString
}

fun List<TileState>.toTileStateString(): String {
    var guessString: String = ""
    this.forEach { tileState ->
        guessString += tileState.toInt()
    }
    return guessString
}
