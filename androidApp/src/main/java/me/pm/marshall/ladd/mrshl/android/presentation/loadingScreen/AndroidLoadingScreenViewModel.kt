package me.pm.marshall.ladd.mrshl.android.presentation.loadingScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import me.pm.marshall.ladd.mrshl.core.flows.MultiplatformStateFlow
import me.pm.marshall.ladd.mrshl.domain.useCases.CachePuzzlesFromRemoteUseCase
import me.pm.marshall.ladd.mrshl.presentation.loadingScreen.LoadingScreenViewModel
import me.pm.marshall.ladd.mrshl.presentation.loadingScreen.model.LoadingScreenEvent
import me.pm.marshall.ladd.mrshl.presentation.loadingScreen.model.LoadingScreenState
import javax.inject.Inject

@HiltViewModel
class AndroidLoadingScreenViewModel @Inject constructor(
    private val cachePuzzles: CachePuzzlesFromRemoteUseCase,
) : ViewModel() {

    private val viewModel by lazy {
        LoadingScreenViewModel(
            cachePuzzles = cachePuzzles,
            coroutineScope = viewModelScope,
        )
    }

    val state: MultiplatformStateFlow<LoadingScreenState> = viewModel.state

    fun onEvent(event: LoadingScreenEvent) {
        viewModel.onEvent(event)
    }
}
