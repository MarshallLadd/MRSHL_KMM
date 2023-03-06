package me.pm.marshall.ladd.mrshl.core.network.answers

import me.pm.marshall.ladd.mrshl.core.network.answers.model.AllAnswersNetworkDTO
import me.pm.marshall.ladd.mrshl.core.network.answers.model.LatestAnswerNetworkDTO

interface AnswersApiInterface {
    suspend fun getLatestAnswer(): LatestAnswerNetworkDTO

    suspend fun getAllAnswers(): List<AllAnswersNetworkDTO>
}
