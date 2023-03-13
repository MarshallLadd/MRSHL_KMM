package me.pm.marshall.ladd.mrshl.presentation.loadingScreen

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.pm.marshall.ladd.mrshl.core.Result
import me.pm.marshall.ladd.mrshl.core.flows.toMultiplatformStateFlow
import me.pm.marshall.ladd.mrshl.core.network.NetworkException
import me.pm.marshall.ladd.mrshl.domain.useCases.CachePuzzlesFromRemote
import me.pm.marshall.ladd.mrshl.presentation.loadingScreen.model.LoadingScreenEvent
import me.pm.marshall.ladd.mrshl.presentation.loadingScreen.model.LoadingScreenState

class LoadingScreenViewModel(
    private val cachePuzzles: CachePuzzlesFromRemote,
    private val coroutineScope: CoroutineScope?,
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(
        value = LoadingScreenState(),
    )
    val state = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(2000),
        initialValue = _state.value,
    ).toMultiplatformStateFlow()

    init {
//        cacheRemotePuzzles()
        viewModelScope.launch(Dispatchers.Default) {
            delay(2000)
            onEvent(LoadingScreenEvent.EventsLoaded)
        }
    }

    fun onEvent(event: LoadingScreenEvent) {
        when (event) {
            LoadingScreenEvent.ErrorSeen -> {
                _state.update {
                    it.copy(error = null)
                }
            }

            LoadingScreenEvent.EventsLoaded -> {
                _state.update {
                    it.copy(
                        loadingComplete = true,
                        isLoading = false,
                        error = null,
                    )
                }
            }

            is LoadingScreenEvent.FailedToGetEvents -> {
                _state.update {
                    it.copy(
                        error = event.error,
                        isLoading = false,
                    )
                }
            }

            LoadingScreenEvent.LoadingEvents -> {
                _state.update {
                    it.copy(isLoading = true)
                }
            }
        }
    }

    private fun cacheRemotePuzzles() {
        if (state.value.isLoading) return
        viewModelScope.launch {
            onEvent(LoadingScreenEvent.LoadingEvents)
            when (val result = cachePuzzles.execute()) {
                is Result.Error -> {
                    onEvent(LoadingScreenEvent.FailedToGetEvents(error = (result.throwable as NetworkException).error))
                }

                is Result.Success -> {
                    onEvent(LoadingScreenEvent.EventsLoaded)
                }
            }
        }
    }
}
