package me.pm.marshall.ladd.mrshl.presentation.core

sealed class TileState(val letter: Char?) {
    class UnsubmittedGuess(letter: Char?): TileState(letter)
    class BadGuess(letter: Char): TileState(letter)
    class GoodGuess(letter: Char): TileState(letter)
    class WrongLocationGuess(letter: Char): TileState(letter)
}