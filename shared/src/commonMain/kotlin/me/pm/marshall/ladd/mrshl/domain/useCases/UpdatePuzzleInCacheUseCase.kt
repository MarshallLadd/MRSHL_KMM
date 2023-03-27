package me.pm.marshall.ladd.mrshl.domain.useCases

import database.PuzzleEntity
import me.pm.marshall.ladd.mrshl.core.Result
import me.pm.marshall.ladd.mrshl.core.database.PuzzleDatabaseOperations

class UpdatePuzzleInCacheUseCase(
    private val database: PuzzleDatabaseOperations,
) {

    suspend fun execute(update: PuzzleEntity): Result<Unit> {
        return try {
            database.updatePuzzle(update)
            Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}
