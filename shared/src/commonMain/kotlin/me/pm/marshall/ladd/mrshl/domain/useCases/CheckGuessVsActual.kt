package me.pm.marshall.ladd.mrshl.domain.useCases

import me.pm.marshall.ladd.mrshl.presentation.core.TileState

class CheckGuessVsActual {

    fun execute(
        actualWord: String,
        guessWord: String
    ): List<TileState> {
        return if (guessWord == actualWord) {
            guessWord.map { TileState.GoodGuess(it) }
        } else {
            guessWord.map { TileState.BadGuess(it) }
        }
    }
}
