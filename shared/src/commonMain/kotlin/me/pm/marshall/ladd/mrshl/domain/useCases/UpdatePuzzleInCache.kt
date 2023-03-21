package me.pm.marshall.ladd.mrshl.domain.useCases

import me.pm.marshall.ladd.mrshl.core.Result
import me.pm.marshall.ladd.mrshl.core.database.sqlDelight.PuzzleDatabaseSqlDelightImpl
import me.pm.marshall.ladd.mrshl.core.mappers.toPuzzleDbEntity
import me.pm.marshall.ladd.mrshl.presentation.core.PuzzleForPlay

class UpdatePuzzleInCache(
    private val database: PuzzleDatabaseSqlDelightImpl,
) {

    suspend fun execute(update: PuzzleForPlay): Result<Unit> {
        return try {
            database.updatePuzzle(update.toPuzzleDbEntity())
            Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}
