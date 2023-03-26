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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import me.pm.marshall.ladd.mrshl.android.presentation.playPuzzle.composables.GameBoard
import me.pm.marshall.ladd.mrshl.android.presentation.playPuzzle.composables.Keyboard

data class PlayPuzzleScreen(private val puzzleId: Long) : AndroidScreen() {

    @Composable
    override fun Content() {
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
                    boardState = emptyList(),
                )
                Keyboard(
                    modifier = Modifier
//                        .padding(WindowInsets.navigationBars.asPaddingValues())
//                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    onLetterPress = {},
                    onDeletePress = {},
                    onEnterPress = {},
                )
            }
        }
    }
}
