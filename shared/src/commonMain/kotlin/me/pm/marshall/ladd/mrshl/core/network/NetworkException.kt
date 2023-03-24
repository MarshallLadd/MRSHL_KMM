package me.pm.marshall.ladd.mrshl.core.network

/**
 *
 */
enum class NetworkError {
    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR,
    PARSING_ERROR,
}

class NetworkException(val error: NetworkError) : Exception(
    "A network error occurred: $error",
)
