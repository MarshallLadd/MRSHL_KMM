package me.pm.marshall.ladd.mrshl.core.database

import database.PuzzleEntity
import me.pm.marshall.ladd.mrshl.core.flows.MultiplatformFlow
import me.pm.marshall.ladd.mrshl.presentation.puzzleScreen.Puzzle

interface PuzzleDatabaseOperations {
    fun getAllPuzzlesAsFlow(): MultiplatformFlow<List<Puzzle>>
    fun getAllPuzzlesAsList(): List<Puzzle>
    fun getPuzzleById(requestedId: Long): Puzzle
    suspend fun insertNewPuzzle(entry: PuzzleEntity)
    suspend fun updatePuzzle(entry: PuzzleEntity)
}
