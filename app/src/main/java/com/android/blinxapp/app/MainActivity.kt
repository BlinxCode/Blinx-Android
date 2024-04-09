package com.android.blinxapp.app


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import com.android.blinxapp.feature.presentation.components.common.BlinxStatusBarColor
import com.android.blinxapp.feature.presentation.components.navigation.AuthNavigationRoute
import com.android.blinxapp.feature.presentation.components.navigation.Screen
import com.android.blinxapp.feature.presentation.components.navigation.SignupNavigation
import com.android.blinxapp.feature.presentation.viewmodel.AuthViewModel
import com.android.blinxapp.ui.theme.*
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
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
                    navController = rememberAnimatedNavController()

                    //Check if user is authenticated and navigate to correct screen
                    SignupNavigation(navController = navController, if (viewModel.isUserAuthenticated)
                        Screen.PinScreen.route else AuthNavigationRoute.Onboarding.name)
                }
            }
        }
    }
}

