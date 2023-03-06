package me.pm.marshall.ladd.mrshl.core.network.guessCheck.model

import kotlinx.serialization.Serializable

@Serializable
data class GuessCheckResponseDTO(
    val success: Boolean?,
    val message: String?,
    val word: String?,
)
