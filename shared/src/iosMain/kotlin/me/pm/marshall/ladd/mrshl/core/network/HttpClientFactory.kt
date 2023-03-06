package me.pm.marshall.ladd.mrshl.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual class HttpClientFactory {
    actual fun create(): HttpClient {
        return HttpClient(Darwin) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        coerceInputValues = true
                    },
                )
            }
        }
    }
}
