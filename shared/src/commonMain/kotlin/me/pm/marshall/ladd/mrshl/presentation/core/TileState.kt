package me.pm.marshall.ladd.mrshl.presentation.core

/**
 * Represents the possible states a square may be in during play.
 */
sealed class TileState(val letter: Char?) {
    class UnsubmittedGuess(letter: Char?) : TileState(letter)
    class BadGuess(letter: Char) : TileState(letter)
    class GoodGuess(letter: Char) : TileState(letter)
    class WrongLocationGuess(letter: Char) : TileState(letter)
}
