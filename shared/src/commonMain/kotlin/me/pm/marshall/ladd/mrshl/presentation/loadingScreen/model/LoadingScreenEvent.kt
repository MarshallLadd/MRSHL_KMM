package me.pm.marshall.ladd.mrshl.presentation.loadingScreen.model

import me.pm.marshall.ladd.mrshl.core.network.NetworkError

sealed class LoadingScreenEvent {
    object LoadingEvents : LoadingScreenEvent()
    object EventsLoaded : LoadingScreenEvent()
    data class FailedToGetEvents(val error: NetworkError) : LoadingScreenEvent()
    object ErrorSeen : LoadingScreenEvent()
}
