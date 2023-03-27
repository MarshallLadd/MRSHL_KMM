package me.pm.marshall.ladd.mrshl.core.database.sqlDelight

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import database.PuzzleEntity
import kotlinx.coroutines.flow.map
import me.pm.marshall.ladd.mrshl.core.database.PuzzleDatabaseOperations
import me.pm.marshall.ladd.mrshl.core.flows.MultiplatformFlow
import me.pm.marshall.ladd.mrshl.core.flows.toMultiplatformFlow
import me.pm.marshall.ladd.mrshl.core.mappers.toPuzzleForPlay
import me.pm.marshall.ladd.mrshl.database.MrshlDatabase
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

    override fun getAllUnplayedPuzzlesAsList(): List<PuzzleEntity> {
        return queries
            .getUnplayedPuzzles()
            .executeAsList()
    }

    override fun getPuzzleByIdAsFlow(requestedId: Long): MultiplatformFlow<PuzzleForPlay> {
        return queries
            .getPuzzleById(requestedId)
            .asFlow()
            .mapToOne()
            .map { it.toPuzzleForPlay() }
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
            numberOfGuesses = 0
        )
    }

    override suspend fun updatePuzzle(entry: PuzzleEntity) {
        queries.updatePuzzle(
            puzzleId = entry.id,
            guessString = entry.guessString,
            tileStatusString = entry.tileStatusString,
            completedDate = entry.completedDate,
            numberOfGuesses = entry.numberOfGuesses
        )
    }
}
