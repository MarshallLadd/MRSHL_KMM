package me.pm.marshall.ladd.mrshl.presentation.loadingScreen

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.pm.marshall.ladd.mrshl.core.Result
import me.pm.marshall.ladd.mrshl.core.flows.toMultiplatformStateFlow
import me.pm.marshall.ladd.mrshl.core.network.NetworkException
import me.pm.marshall.ladd.mrshl.domain.useCases.CachePuzzlesFromRemoteUseCase
import me.pm.marshall.ladd.mrshl.presentation.loadingScreen.model.LoadingScreenEvent
import me.pm.marshall.ladd.mrshl.presentation.loadingScreen.model.LoadingScreenState

class LoadingScreenViewModel(
    private val cachePuzzles: CachePuzzlesFromRemoteUseCase,
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
        onEvent(LoadingScreenEvent.LoadingPuzzles)
    }

    fun onEvent(event: LoadingScreenEvent) {
        when (event) {
            LoadingScreenEvent.ErrorSeen -> {
                _state.update {
                    it.copy(error = null)
                }
            }

            LoadingScreenEvent.PuzzlesLoaded -> {
                _state.update {
                    LoadingScreenState(
                        loadingComplete = true,
                        isLoading = false,
                        error = null,
                    )
                }
            }

            is LoadingScreenEvent.FailedToGetPuzzles -> {
                _state.update {
                    LoadingScreenState(
                        loadingComplete = false,
                        isLoading = false,
                        error = event.error,
                    )
                }
            }

            LoadingScreenEvent.LoadingPuzzles -> {
                _state.update {
                    LoadingScreenState(
                        loadingComplete = false,
                        isLoading = true,
                        error = null,
                    )
                }
                cacheRemotePuzzles()
            }
        }
    }

    private fun cacheRemotePuzzles() {
        viewModelScope.launch {
            when (val result = cachePuzzles.execute()) {
                is Result.Error -> {
                    onEvent(LoadingScreenEvent.FailedToGetPuzzles(error = (result.throwable as NetworkException).error))
                }

                is Result.Success -> {
                    onEvent(LoadingScreenEvent.PuzzlesLoaded)
                }
            }
        }
    }
}
