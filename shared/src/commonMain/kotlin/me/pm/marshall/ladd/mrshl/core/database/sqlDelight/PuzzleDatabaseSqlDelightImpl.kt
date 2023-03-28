package me.pm.marshall.ladd.mrshl.core.database.sqlDelight

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import database.PuzzleEntity
import me.pm.marshall.ladd.mrshl.core.database.PuzzleDatabaseOperations
import me.pm.marshall.ladd.mrshl.core.flows.MultiplatformFlow
import me.pm.marshall.ladd.mrshl.core.flows.toMultiplatformFlow
import me.pm.marshall.ladd.mrshl.database.MrshlDatabase

class PuzzleDatabaseSqlDelightImpl(
    database: MrshlDatabase,
) : PuzzleDatabaseOperations {

    private val queries = database.mrshlQueries

    override fun getAllPuzzlesAsFlow(): MultiplatformFlow<List<PuzzleEntity>> {
        return queries
            .getAllPuzzles()
            .asFlow()
            .mapToList()
            .toMultiplatformFlow()
    }

    override fun getAllPuzzlesAsList(): List<PuzzleEntity> {
        return queries
            .getAllPuzzles()
            .executeAsList()
    }

    override fun getAllUnplayedPuzzlesAsList(): List<PuzzleEntity> {
        return queries
            .getUnplayedPuzzles()
            .executeAsList()
    }

    override fun getPuzzleByIdAsFlow(requestedId: Long): MultiplatformFlow<PuzzleEntity> {
        return queries
            .getPuzzleById(requestedId)
            .asFlow()
            .mapToOne()
            .toMultiplatformFlow()
    }

    override fun getPuzzleById(requestedId: Long): PuzzleEntity {
        return queries
            .getPuzzleById(requestedId)
            .executeAsOne()
    }

    override suspend fun insertNewPuzzle(entry: PuzzleEntity) {
        queries.insertNewPuzzle(
            id = entry.id,
            answer = entry.answer,
            guessString = entry.guessString,
            tileStatusString = entry.tileStatusString,
            puzzleDate = entry.puzzleDate,
            completedDate = entry.completedDate,
            numberOfGuesses = 0,
        )
    }

    override suspend fun updatePuzzle(entry: PuzzleEntity) {
        queries.updatePuzzle(
            puzzleId = entry.id,
            guessString = entry.guessString,
            tileStatusString = entry.tileStatusString,
            completedDate = entry.completedDate,
            numberOfGuesses = entry.numberOfGuesses,
        )
    }
}
