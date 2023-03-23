package me.pm.marshall.ladd.mrshl.android.presentation.playPuzzle

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.androidx.AndroidScreen

data class PlayPuzzleScreen(private val puzzleId: Long) : AndroidScreen() {

    @Composable
    override fun Content() {
        Scaffold() {
            Box(modifier = Modifier.padding(it)) {
                Text(
                    modifier = Modifier.statusBarsPadding(),
                    text = "Selected Puzzle ID: $puzzleId",
                )
            }
        }
    }
}
