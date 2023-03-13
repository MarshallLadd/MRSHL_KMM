package me.pm.marshall.ladd.mrshl.android.presentation.puzzleHistory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.androidx.AndroidScreen


object PuzzleHistoryScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        Scaffold() {
            Box(Modifier.padding(it)) {
                Text(
                    modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues()),
                    text = "Puzzle History Screen"
                )
            }
        }
    }
}