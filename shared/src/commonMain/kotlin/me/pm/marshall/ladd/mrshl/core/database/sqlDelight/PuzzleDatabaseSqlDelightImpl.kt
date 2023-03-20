package me.pm.marshall.ladd.mrshl.core.database.sqlDelight

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import database.PuzzleEntity
import kotlinx.coroutines.flow.map
import me.pm.marshall.ladd.mrshl.core.database.PuzzleDatabaseOperations
import me.pm.marshall.ladd.mrshl.core.flows.MultiplatformFlow
import me.pm.marshall.ladd.mrshl.core.flows.toMultiplatformFlow
import me.pm.marshall.ladd.mrshl.database.MrshlDatabase
import me.pm.marshall.ladd.mrshl.core.mappers.toPuzzleEntry
import me.pm.marshall.ladd.mrshl.presentation.core.Puzzle

class PuzzleDatabaseSqlDelightImpl(
    database: MrshlDatabase,
) : PuzzleDatabaseOperations {

    private val queries = database.mrshlQueries

    override fun getAllPuzzlesAsFlow(): MultiplatformFlow<List<Puzzle>> {
        return queries
            .getAllPuzzles()
            .asFlow()
            .mapToList()
            .map {
                it.map { puzzleEntity ->
                    puzzleEntity.toPuzzleEntry()
                }
            }
            .toMultiplatformFlow()
    }

    override fun getAllPuzzlesAsList(): List<Puzzle> {
        return queries
            .getAllPuzzles()
            .executeAsList()
            .map { puzzleEntity ->
                puzzleEntity.toPuzzleEntry()
            }
    }

    override fun getPuzzleById(requestedId: Long): Puzzle {
        return queries
            .getPuzzleById(requestedId)
            .executeAsOne()
            .toPuzzleEntry()
    }

    override suspend fun insertNewPuzzle(entry: PuzzleEntity) {
        queries.insertNewPuzzle(
            id = entry.id,
            answer = entry.answer,
            guessString = entry.guessString,
            puzzleDate = entry.puzzleDate,
            completedDate = entry.completedDate,
        )
    }

    override suspend fun updatePuzzle(entry: PuzzleEntity) {
        queries.updatePuzzle(
            id = entry.id,
            answer = entry.answer,
            guessString = entry.guessString,
            completedDate = entry.completedDate,
        )
    }
}
