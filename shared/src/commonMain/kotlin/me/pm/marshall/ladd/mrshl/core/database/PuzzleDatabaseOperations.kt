package me.pm.marshall.ladd.mrshl.core.database

import database.PuzzleEntity
import me.pm.marshall.ladd.mrshl.core.flows.MultiplatformFlow
import me.pm.marshall.ladd.mrshl.presentation.core.PuzzleForPlay

interface PuzzleDatabaseOperations {
    fun getAllPuzzlesAsFlow(): MultiplatformFlow<List<PuzzleForPlay>>
    fun getAllPuzzlesAsList(): List<PuzzleForPlay>
    fun getAllUnplayedPuzzlesAsList(): List<PuzzleEntity>
    fun getPuzzleByIdAsFlow(requestedId: Long): MultiplatformFlow<PuzzleForPlay>
    fun getPuzzleById(requestedId: Long): PuzzleEntity
    suspend fun insertNewPuzzle(entry: PuzzleEntity)
    suspend fun updatePuzzle(entry: PuzzleEntity)
}
