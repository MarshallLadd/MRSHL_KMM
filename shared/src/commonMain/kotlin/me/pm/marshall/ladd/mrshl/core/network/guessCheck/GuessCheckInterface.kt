package me.pm.marshall.ladd.mrshl.core.network.guessCheck

interface GuessCheckInterface {
    suspend fun isGuessValid(guess: String): Boolean
}
