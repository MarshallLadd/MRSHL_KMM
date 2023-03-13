package me.pm.marshall.ladd.mrshl.domain.useCases

import io.github.aakira.napier.Napier
import me.pm.marshall.ladd.mrshl.core.database.sqlDelight.PuzzleDatabaseSqlDelightImpl
import me.pm.marshall.ladd.mrshl.domain.model.toUIPuzzleHistoryEntity
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.UIPuzzleHistoryEntity
import me.pm.marshall.ladd.mrshl.core.Result

class GetPuzzleHistoryFromCache(
    private val database: PuzzleDatabaseSqlDelightImpl,
) {

    suspend fun execute(): Result<List<UIPuzzleHistoryEntity>> {
        return try {
            database.getAllPuzzlesAsList().map { it.toUIPuzzleHistoryEntity() }
            Result.Success(emptyList())
        } catch (e: Exception) {
            Napier.e(e.message ?: "", e)
            Result.Error(e)
        }
    }
}