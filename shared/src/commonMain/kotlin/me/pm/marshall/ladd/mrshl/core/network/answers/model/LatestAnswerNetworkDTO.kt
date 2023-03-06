package me.pm.marshall.ladd.mrshl.core.network.answers.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LatestAnswerNetworkDTO(
    @SerialName("today") val answer: String,
)
