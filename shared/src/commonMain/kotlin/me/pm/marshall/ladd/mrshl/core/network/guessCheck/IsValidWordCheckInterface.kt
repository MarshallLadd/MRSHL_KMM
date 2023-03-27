package me.pm.marshall.ladd.mrshl.core.network.guessCheck

interface IsValidWordCheckInterface {
    suspend fun isGuessValid(guess: String): Boolean
}
