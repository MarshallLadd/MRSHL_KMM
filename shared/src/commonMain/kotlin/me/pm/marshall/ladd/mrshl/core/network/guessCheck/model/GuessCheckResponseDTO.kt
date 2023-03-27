package me.pm.marshall.ladd.mrshl.core.network.guessCheck.model

import kotlinx.serialization.Serializable

@Serializable
data class GuessCheckResponseDTO(
    val word: String? = null,
)
