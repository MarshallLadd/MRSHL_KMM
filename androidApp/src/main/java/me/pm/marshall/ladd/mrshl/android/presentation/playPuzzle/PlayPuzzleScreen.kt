@file:OptIn(ExperimentalMaterialApi::class)

package me.pm.marshall.ladd.mrshl.android.presentation.playPuzzle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import me.pm.marshall.ladd.mrshl.android.BaseApplication
import me.pm.marshall.ladd.mrshl.android.presentation.core.di.AppModule
import me.pm.marshall.ladd.mrshl.android.presentation.playPuzzle.composables.GameBoard
import me.pm.marshall.ladd.mrshl.android.presentation.playPuzzle.composables.Keyboard
import me.pm.marshall.ladd.mrshl.presentation.playPuzzle.model.PlayPuzzleEvent
import me.pm.marshall.ladd.mrshl.presentation.playPuzzle.model.PlayPuzzleState

data class PlayPuzzleScreen(
    private val puzzleId: Long
) : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: AndroidPlayPuzzleViewModel =
            getViewModel<AndroidPlayPuzzleViewModel>(viewModelProviderFactory = object :
                ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val databaseOperations = AppModule.providesDatabaseOperations(
                        AppModule.providesMrshlDatabase(BaseApplication.INSTANCE)
                    )
                    return AndroidPlayPuzzleViewModel(
                        puzzleId = puzzleId,
                        validateGuess = AppModule.providesValidateGuess(
                            AppModule.providesGuessCheckInterface(
                                AppModule.providesAnswersHttpClient()
                            )
                        ),
                        updatePuzzleInCache = AppModule.providesUpdatePuzzleInCache(
                            databaseOperations
                        ),
                        databaseOperations = databaseOperations
                    ) as T
                }
            })
        val state: PlayPuzzleState by viewModel.state.collectAsState()
        Scaffold() {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                GameBoard(
                    modifier = Modifier
                        .padding(WindowInsets.statusBars.asPaddingValues())
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    boardState = state.tileState ?: emptyList(),
                )
                Keyboard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    onLetterPress = {
                        viewModel.onEvent(PlayPuzzleEvent.KeyboardLetterClicked(letter = it))
                    },
                    onDeletePress = {
                        viewModel.onEvent(PlayPuzzleEvent.KeyboardDeleteClicked)
                    },
                    onEnterPress = {
                        viewModel.onEvent(PlayPuzzleEvent.KeyboardEnterClicked)
                    },
                )
            }
        }
    }
}
