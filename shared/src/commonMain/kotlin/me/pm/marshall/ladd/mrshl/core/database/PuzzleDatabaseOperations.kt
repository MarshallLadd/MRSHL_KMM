package me.pm.marshall.ladd.mrshl.core.database

import database.PuzzleEntity
import me.pm.marshall.ladd.mrshl.core.flows.MultiplatformFlow

interface PuzzleDatabaseOperations {
    fun getAllPuzzlesAsFlow(): MultiplatformFlow<List<PuzzleEntity>>
    fun getAllPuzzlesAsList(): List<PuzzleEntity>
    fun getAllUnplayedPuzzlesAsList(): List<PuzzleEntity>
    fun getPuzzleByIdAsFlow(requestedId: Long): MultiplatformFlow<PuzzleEntity>
    fun getPuzzleById(requestedId: Long): PuzzleEntity
    suspend fun insertNewPuzzle(entry: PuzzleEntity)
    suspend fun updatePuzzle(entry: PuzzleEntity)
}
