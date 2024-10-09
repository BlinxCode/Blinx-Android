package com.android.blinxapp.app


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.android.blinxapp.ui.feature.presentation.components.common.BlinxStatusBarColor
import com.android.blinxapp.ui.feature.presentation.components.navigation.DashBoardNavigation
import com.android.blinxapp.ui.theme.*
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalAnimationApi
class DashboardActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BlinxAppTheme {

                BlinxStatusBarColor(window, this)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary,
                ) {

                    navController = rememberAnimatedNavController()

                    DashBoardNavigation(navController = navController)

                }
            }
        }
    }

}

