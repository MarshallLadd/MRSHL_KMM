package me.pm.marshall.ladd.mrshl.domain.useCases

import me.pm.marshall.ladd.mrshl.core.Result
import me.pm.marshall.ladd.mrshl.core.database.sqlDelight.PuzzleDatabaseSqlDelightImpl
import me.pm.marshall.ladd.mrshl.core.network.NetworkException
import me.pm.marshall.ladd.mrshl.core.network.answers.AnswersApiKtorImpl
import me.pm.marshall.ladd.mrshl.domain.model.toPuzzleEntity

class CachePuzzlesFromRemote(
    private val answersClient: AnswersApiKtorImpl,
    private val databaseOperations: PuzzleDatabaseSqlDelightImpl,
) {

    suspend fun execute(): Result<Unit> {
        return try {
            val allRemoteAnswers = answersClient.getAllAnswers().sortedByDescending { it.id }

            (databaseOperations.getAllPuzzlesAsList().size..allRemoteAnswers.lastIndex)
                .forEach { index ->
                    databaseOperations.insertNewPuzzle(allRemoteAnswers[index].toPuzzleEntity())
                }

            Result.Success(Unit)
        } catch (e: NetworkException) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}
