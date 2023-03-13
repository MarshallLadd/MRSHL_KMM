package me.pm.marshall.ladd.mrshl.core.network.answers

import me.pm.marshall.ladd.mrshl.core.network.answers.model.AllPuzzlesNetworkDTO
import me.pm.marshall.ladd.mrshl.core.network.answers.model.LatestPuzzleNetworkDTO

interface PuzzlesApiInterface {
    suspend fun getLatestPuzzle(): LatestPuzzleNetworkDTO

    suspend fun getAllPuzzles(): List<AllPuzzlesNetworkDTO>
}
