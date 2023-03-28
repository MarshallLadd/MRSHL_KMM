package me.pm.marshall.ladd.mrshl.presentation.playPuzzle.model

import me.pm.marshall.ladd.mrshl.core.network.NetworkError
import me.pm.marshall.ladd.mrshl.presentation.core.TileState

data class PlayPuzzleState(
    val puzzleId: Long,
    val tileState: List<TileState>,
    val numberOfGuesses: Int = 0,
    val puzzleComplete: Boolean = false,
    val networkBusy: Boolean = false,
    val error: NetworkError? = null,
)
