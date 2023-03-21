package me.pm.marshall.ladd.mrshl.core.database.sqlDelight

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import database.PuzzleDbEntity
import kotlinx.coroutines.flow.map
import me.pm.marshall.ladd.mrshl.core.database.PuzzleDatabaseOperations
import me.pm.marshall.ladd.mrshl.core.flows.MultiplatformFlow
import me.pm.marshall.ladd.mrshl.core.flows.toMultiplatformFlow
import me.pm.marshall.ladd.mrshl.database.MrshlDatabase
import me.pm.marshall.ladd.mrshl.core.mappers.toPuzzleForPlay
import me.pm.marshall.ladd.mrshl.presentation.core.PuzzleForPlay

class PuzzleDatabaseSqlDelightImpl(
    database: MrshlDatabase,
) : PuzzleDatabaseOperations {

    private val queries = database.mrshlQueries

    override fun getAllPuzzlesAsFlow(): MultiplatformFlow<List<PuzzleForPlay>> {
        return queries
            .getAllPuzzles()
            .asFlow()
            .mapToList()
            .map {
                it.map { puzzleEntity ->
                    puzzleEntity.toPuzzleForPlay()
                }
            }
            .toMultiplatformFlow()
    }

    override fun getAllPuzzlesAsList(): List<PuzzleForPlay> {
        return queries
            .getAllPuzzles()
            .executeAsList()
            .map { puzzleEntity ->
                puzzleEntity.toPuzzleForPlay()
            }
    }

    override fun getPuzzleById(requestedId: Long): PuzzleForPlay {
        return queries
            .getPuzzleById(requestedId)
            .executeAsOne()
            .toPuzzleForPlay()
    }

    override suspend fun insertNewPuzzle(entry: PuzzleDbEntity) {
        queries.insertNewPuzzle(
            id = entry.id,
            answer = entry.answer,
            guessString = entry.guessString,
            puzzleDate = entry.puzzleDate,
            completedDate = entry.completedDate,
        )
    }

    override suspend fun updatePuzzle(entry: PuzzleDbEntity) {
        queries.updatePuzzle(
            id = entry.id,
            answer = entry.answer,
            guessString = entry.guessString,
            completedDate = entry.completedDate,
        )
    }
}
