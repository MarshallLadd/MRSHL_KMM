package me.pm.marshall.ladd.mrshl.core.network

import io.ktor.client.HttpClient

expect class HttpClientFactory {
    fun create(): HttpClient
}
