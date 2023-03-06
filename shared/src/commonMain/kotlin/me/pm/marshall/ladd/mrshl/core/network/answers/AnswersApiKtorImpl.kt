package me.pm.marshall.ladd.mrshl.core.network.answers

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException
import me.pm.marshall.ladd.mrshl.core.network.NetworkError
import me.pm.marshall.ladd.mrshl.core.network.NetworkException
import me.pm.marshall.ladd.mrshl.core.network.answers.model.AllAnswersNetworkDTO
import me.pm.marshall.ladd.mrshl.core.network.answers.model.LatestAnswerNetworkDTO
import me.pm.marshall.ladd.mrshl.core.secrets.Keys

class AnswersApiKtorImpl(
    private val httpClient: HttpClient,
) : AnswersApiInterface {

    companion object {
        const val BASE_URL = "https://wordle-answers-solutions.p.rapidapi.com/"
        const val API_HOST_STRING = "wordle-answers-solutions.p.rapidapi.com"
    }

    override suspend fun getLatestAnswer(): LatestAnswerNetworkDTO {
        val result = try {
            httpClient.get {
                url(urlString = BASE_URL + "today")
                headers {
                    append("X-RapidAPI-Key", Keys.ANSWERS_API_KEY)
                    append("X-RapidAPI-Host", API_HOST_STRING)
                }
                contentType(ContentType.Application.Json)
            }
        } catch (e: IOException) {
            throw Exception()
        }
        when (result.status.value) {
            in 200..299 -> Unit
            500 -> throw NetworkException(NetworkError.SERVER_ERROR)
            in 400..499 -> throw NetworkException(NetworkError.CLIENT_ERROR)
            else -> throw NetworkException(NetworkError.UNKNOWN_ERROR)
        }
        return try {
            result.body<LatestAnswerNetworkDTO>()
        } catch (e: Exception) {
            throw NetworkException(NetworkError.PARSING_ERROR)
        }
    }

    override suspend fun getAllAnswers(): List<AllAnswersNetworkDTO> {
        val result = try {
            httpClient.get {
                url(urlString = BASE_URL + "answers")
                headers {
                    append("X-RapidAPI-Key", Keys.ANSWERS_API_KEY)
                    append("X-RapidAPI-Host", API_HOST_STRING)
                }
                contentType(ContentType.Application.Json)
            }
        } catch (e: IOException) {
            throw Exception()
        }
        when (result.status.value) {
            in 200..299 -> Unit
            500 -> throw NetworkException(NetworkError.SERVER_ERROR)
            in 400..499 -> throw NetworkException(NetworkError.CLIENT_ERROR)
            else -> throw NetworkException(NetworkError.UNKNOWN_ERROR)
        }
        return try {
            result.body<List<AllAnswersNetworkDTO>>()
        } catch (e: Exception) {
            throw NetworkException(NetworkError.PARSING_ERROR)
        }
    }
}
