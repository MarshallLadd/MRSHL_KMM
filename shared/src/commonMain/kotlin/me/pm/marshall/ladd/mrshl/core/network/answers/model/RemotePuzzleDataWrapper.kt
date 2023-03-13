package me.pm.marshall.ladd.mrshl.core.network.answers.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemotePuzzleDataWrapper<T>(
    @SerialName("data") val data: List<T>
)