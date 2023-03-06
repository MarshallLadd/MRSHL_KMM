package me.pm.marshall.ladd.mrshl.presentation.loadingScreen.model

import me.pm.marshall.ladd.mrshl.core.network.NetworkError

data class LoadingScreenState(
    val isLoading: Boolean = false,
    val loadingComplete: Boolean = false,
    val error: NetworkError? = null,
)
