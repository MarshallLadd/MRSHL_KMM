package me.pm.marshall.ladd.mrshl.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.view.WindowCompat
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dagger.hilt.android.AndroidEntryPoint
import me.pm.marshall.ladd.mrshl.android.presentation.core.theme.MrshlBaseComposable
import me.pm.marshall.ladd.mrshl.android.presentation.loadingScreen.LoadingSplashScreen

@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MrshlBaseComposable {
                Navigator(screen = LoadingSplashScreen) {
                    SlideTransition(navigator = it)
                }
            }
        }
    }
}
