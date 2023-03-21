package me.pm.marshall.ladd.mrshl.core.database

import database.PuzzleDbEntity
import me.pm.marshall.ladd.mrshl.core.flows.MultiplatformFlow
import me.pm.marshall.ladd.mrshl.presentation.core.PuzzleForPlay

interface PuzzleDatabaseOperations {
    fun getAllPuzzlesAsFlow(): MultiplatformFlow<List<PuzzleForPlay>>
    fun getAllPuzzlesAsList(): List<PuzzleForPlay>
    fun getPuzzleById(requestedId: Long): PuzzleForPlay
    suspend fun insertNewPuzzle(entry: PuzzleDbEntity)
    suspend fun updatePuzzle(entry: PuzzleDbEntity)
}
