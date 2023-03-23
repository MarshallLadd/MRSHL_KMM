package me.pm.marshall.ladd.mrshl.core.network.guessCheck

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException
import me.pm.marshall.ladd.mrshl.core.network.Keys
import me.pm.marshall.ladd.mrshl.core.network.NetworkError
import me.pm.marshall.ladd.mrshl.core.network.NetworkException
import me.pm.marshall.ladd.mrshl.core.network.guessCheck.model.GuessCheckResponseDTO

class GuessCheckInterfaceImpl(
    private val httpClient: HttpClient,
) : GuessCheckInterface {

    companion object {
        const val BASE_URL = "https://wordsapiv1.p.rapidapi.com/words/"
        const val API_HOST_STRING = "wordsapiv1.p.rapidapi.com"
    }

    override suspend fun isGuessValid(guess: String): Boolean {
        val result = try {
            httpClient.get {
                url(urlString = BASE_URL + guess)
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
            in 200..299, 404 -> Unit
            500 -> throw NetworkException(NetworkError.SERVER_ERROR)
            in 400..499 -> throw NetworkException(NetworkError.CLIENT_ERROR)
            else -> throw NetworkException(NetworkError.UNKNOWN_ERROR)
        }
        return try {
            !(result.body<GuessCheckResponseDTO>().word.isNullOrBlank())
        } catch (e: Exception) {
            throw NetworkException(NetworkError.PARSING_ERROR)
        }
    }
}
