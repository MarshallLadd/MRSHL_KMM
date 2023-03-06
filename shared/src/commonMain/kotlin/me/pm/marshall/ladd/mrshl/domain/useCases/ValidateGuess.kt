package me.pm.marshall.ladd.mrshl.domain.useCases

import me.pm.marshall.ladd.mrshl.core.Result
import me.pm.marshall.ladd.mrshl.core.network.NetworkException
import me.pm.marshall.ladd.mrshl.core.network.guessCheck.GuessCheckInterfaceImpl

class ValidateGuess(
    private val guessCheckClient: GuessCheckInterfaceImpl,
) {
    suspend fun execute(guess: String): Result<Boolean> {
        return try {
            Result.Success(guessCheckClient.isGuessValid(guess = guess))
        } catch (e: NetworkException) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}
