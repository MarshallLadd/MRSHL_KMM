package me.pm.marshall.ladd.mrshl.presentation.playPuzzle.model

import me.pm.marshall.ladd.mrshl.core.network.NetworkError
import me.pm.marshall.ladd.mrshl.presentation.core.PuzzleForPlay

data class PlayPuzzleState(
    val puzzle: PuzzleForPlay? = null,
    val validatingGuess: Boolean = false,
    val error: NetworkError? = null,
)
