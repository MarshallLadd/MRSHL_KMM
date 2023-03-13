package me.pm.marshall.ladd.mrshl.presentation.loadingScreen.model

import me.pm.marshall.ladd.mrshl.core.network.NetworkError

sealed class LoadingScreenEvent {
    object LoadingPuzzles : LoadingScreenEvent()
    object PuzzlesLoaded : LoadingScreenEvent()
    data class FailedToGetPuzzles(val error: NetworkError) : LoadingScreenEvent()
    object ErrorSeen : LoadingScreenEvent()
}
