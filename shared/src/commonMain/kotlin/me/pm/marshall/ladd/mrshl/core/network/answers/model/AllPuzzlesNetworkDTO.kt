package me.pm.marshall.ladd.mrshl.core.network.answers.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllPuzzlesNetworkDTO(
    @SerialName("day") val day: String,
    @SerialName("num") val id: String,
    @SerialName("answer") val answer: String,
)

