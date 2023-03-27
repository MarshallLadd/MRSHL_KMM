package me.pm.marshall.ladd.mrshl.android.presentation.playPuzzle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import me.pm.marshall.ladd.mrshl.core.database.PuzzleDatabaseOperations
import me.pm.marshall.ladd.mrshl.core.flows.MultiplatformStateFlow
import me.pm.marshall.ladd.mrshl.domain.useCases.UpdatePuzzleInCacheUseCase
import me.pm.marshall.ladd.mrshl.domain.useCases.IsValidWordCheckUseCase
import me.pm.marshall.ladd.mrshl.presentation.playPuzzle.PlayPuzzleViewModel
import me.pm.marshall.ladd.mrshl.presentation.playPuzzle.model.PlayPuzzleEvent
import me.pm.marshall.ladd.mrshl.presentation.playPuzzle.model.PlayPuzzleState

class AndroidPlayPuzzleViewModel @AssistedInject constructor(
    @Assisted private val puzzleId: Long,
    private val isValidWordCheckUseCase: IsValidWordCheckUseCase,
    private val updatePuzzleInCacheUseCase: UpdatePuzzleInCacheUseCase,
    private val databaseOperations: PuzzleDatabaseOperations,
) : ViewModel() {

    private val viewModel by lazy {
        PlayPuzzleViewModel(
            puzzleId = puzzleId,
            isValidWordCheckUseCase = isValidWordCheckUseCase,
            updatePuzzleInCacheUseCase = updatePuzzleInCacheUseCase,
            databaseOperations = databaseOperations,
            coroutineScope = viewModelScope,
        )
    }

    val state: MultiplatformStateFlow<PlayPuzzleState> = viewModel.state

    fun onEvent(event: PlayPuzzleEvent) {
        viewModel.onEvent(event)
    }
}

@AssistedFactory
interface AndroidPlayPuzzleFactory {
    fun create(puzzleId: Long): AndroidPlayPuzzleViewModel
}
