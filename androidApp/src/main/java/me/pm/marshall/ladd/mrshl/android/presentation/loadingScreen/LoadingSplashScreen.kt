package me.pm.marshall.ladd.mrshl.android.presentation.loadingScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.Navigator
import me.pm.marshall.ladd.mrshl.Greeting
import me.pm.marshall.ladd.mrshl.android.presentation.core.composables.PreviewDevices
import me.pm.marshall.ladd.mrshl.android.presentation.core.composables.guessTile.GuessTile
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.MrshlBaseComposable
import me.pm.marshall.ladd.mrshl.presentation.core.TileState

class LoadingSplashScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = MaterialTheme.colors.background)
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
                    tileState = TileState.WrongLocationGuess('L')
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = MaterialTheme.colors.background),
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

@PreviewDevices
@Composable
fun LoadingSplashScreenPreview() {
    MrshlBaseComposable() {
        Navigator(screen = LoadingSplashScreen())
    }
}