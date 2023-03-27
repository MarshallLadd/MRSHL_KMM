package me.pm.marshall.ladd.mrshl.domain.useCases

import me.pm.marshall.ladd.mrshl.core.Result
import me.pm.marshall.ladd.mrshl.core.database.PuzzleDatabaseOperations
import me.pm.marshall.ladd.mrshl.core.mappers.toPuzzleDbEntity
import me.pm.marshall.ladd.mrshl.core.network.NetworkException
import me.pm.marshall.ladd.mrshl.core.network.answers.PuzzlesApiInterface

class CachePuzzlesFromRemoteUseCase(
    private val puzzleClient: PuzzlesApiInterface,
    private val databaseOperations: PuzzleDatabaseOperations,
) {

    suspend fun execute(): Result<Unit> {
        return try {
            if (databaseOperations.getAllPuzzlesAsList().isEmpty()) {
                puzzleClient
                    .getAllPuzzles()
                    .forEach { databaseOperations.insertNewPuzzle(it.toPuzzleDbEntity()) }
            } else {
                puzzleClient
                    .getAllPuzzles()
                    .filter { it.id.toInt() > databaseOperations.getAllPuzzlesAsList().lastIndex }
                    .forEach {
                        databaseOperations.insertNewPuzzle(it.toPuzzleDbEntity())
                    }
            }
            Result.Success(Unit)
        } catch (e: NetworkException) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}
