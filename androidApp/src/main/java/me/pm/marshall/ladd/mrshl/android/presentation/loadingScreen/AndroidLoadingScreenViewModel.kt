package me.pm.marshall.ladd.mrshl.android.presentation.loadingScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import me.pm.marshall.ladd.mrshl.domain.useCases.CachePuzzlesFromRemote
import me.pm.marshall.ladd.mrshl.presentation.loadingScreen.LoadingScreenViewModel
import me.pm.marshall.ladd.mrshl.presentation.loadingScreen.model.LoadingScreenEvent
import javax.inject.Inject

@HiltViewModel
class AndroidLoadingScreenViewModel @Inject constructor(
    private val cachePuzzles: CachePuzzlesFromRemote,
) : ViewModel() {

    private val viewModel by lazy {
        LoadingScreenViewModel(
            cachePuzzles = cachePuzzles,
            coroutineScope = viewModelScope,
        )
    }

    val state = viewModel.state

    fun onEvent(event: LoadingScreenEvent) {
        viewModel.onEvent(event)
    }
}
