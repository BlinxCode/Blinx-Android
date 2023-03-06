package com.android.blinxapp.navigation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.android.blinxapp.common.BlinxStatusBarColor
import com.android.blinxapp.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Change color status bar.
        //WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            BlinxAppTheme {
                BlinxStatusBarColor(window, this)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary,
                ) {
                   SignupNavigation(this)
                }
            }
        }
    }
}

