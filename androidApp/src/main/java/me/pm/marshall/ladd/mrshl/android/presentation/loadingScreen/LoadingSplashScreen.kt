package me.pm.marshall.ladd.mrshl.android.presentation.loadingScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.pm.marshall.ladd.mrshl.Greeting
import me.pm.marshall.ladd.mrshl.android.presentation.core.composables.PreviewDevices
import me.pm.marshall.ladd.mrshl.android.presentation.core.composables.guessTile.GuessTile
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.MrshlBaseComposable
import me.pm.marshall.ladd.mrshl.android.presentation.puzzleHistory.PuzzleHistoryScreen
import me.pm.marshall.ladd.mrshl.presentation.core.TileState
import me.pm.marshall.ladd.mrshl.presentation.loadingScreen.model.LoadingScreenEvent
import me.pm.marshall.ladd.mrshl.presentation.loadingScreen.model.LoadingScreenState

object LoadingSplashScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: AndroidLoadingScreenViewModel = getViewModel<AndroidLoadingScreenViewModel>()
        val state: LoadingScreenState by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(key1 = state.loadingComplete) {
            when (state.loadingComplete) {
                true -> {
                    navigator replace PuzzleHistoryScreen
                }

                false -> {
                }
            }
        }
        Scaffold { paddingValues ->
            when (state.error) {
                null -> {}
                else -> {
                    AlertDialog(
                        title = {
                            Text(text = "Loading Error", style = MaterialTheme.typography.h4)
                        },
                        text = {
                            Column() {
                                Text(
                                    modifier = Modifier.padding(bottom = 8.dp),
                                    text = "An error has occurred loading new puzzles.",
                                    style = MaterialTheme.typography.body1
                                )
                                Text(
                                    text = "Error: ${state.error?.name}",
                                    style = MaterialTheme.typography.subtitle2
                                )
                            }
                        },
                        confirmButton = {
                            Button(onClick = { viewModel.onEvent(LoadingScreenEvent.PuzzlesLoaded) }) {
                                Text(text = "Continue")
                            }
                        },
                        dismissButton = {
                            Button(onClick = { viewModel.onEvent(LoadingScreenEvent.LoadingPuzzles) }) {
                                Text(text = "Retry")
                            }
                        },
                        onDismissRequest = { viewModel.onEvent(LoadingScreenEvent.ErrorSeen) },
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    val modifier = Modifier.padding(4.dp)
                    GuessTile(
                        modifier = modifier,
                        tileState = TileState.GoodGuess('M')
                    )
                    GuessTile(
                        modifier = modifier,
                        tileState = TileState.BadGuess('R')
                    )
                    GuessTile(
                        modifier = modifier,
                        tileState = TileState.WrongLocationGuess('S')
                    )
                    GuessTile(
                        modifier = modifier,
                        tileState = TileState.GoodGuess('H')
                    )
                    GuessTile(
                        modifier = modifier,
                        tileState = TileState.BadGuess('L')
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    val modifier = Modifier.padding(4.dp)
                    GuessTile(
                        modifier = modifier,
                        tileState = TileState.UnsubmittedGuess(null)
                    )
                    GuessTile(
                        modifier = modifier,
                        tileState = TileState.UnsubmittedGuess(null)
                    )
                    GuessTile(
                        modifier = modifier,
                        tileState = TileState.UnsubmittedGuess(null)
                    )
                    GuessTile(
                        modifier = modifier,
                        tileState = TileState.UnsubmittedGuess(null)
                    )
                    GuessTile(
                        modifier = modifier,
                        tileState = TileState.UnsubmittedGuess(null)
                    )
                }
            }
        }
    }
}

@PreviewDevices
@Composable
fun LoadingSplashScreenPreview() {
    MrshlBaseComposable() {
        Navigator(screen = LoadingSplashScreen)
    }
}