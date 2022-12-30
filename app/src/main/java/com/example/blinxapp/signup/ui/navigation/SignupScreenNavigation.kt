package com.example.blinxapp.signup.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.blinxapp.common.NavigationRoute
import com.example.blinxapp.common.TopBar
import com.example.blinxapp.onboarding.presentation.OnBoardingScreen
import com.example.blinxapp.signup.ui.form.SignupFormScreen
import com.example.blinxapp.signup.ui.get_started.GettingStartedScreen
import com.example.blinxapp.signup.ui.viewmodel.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupNavigation(modifier: Modifier = Modifier, viewModel: SignUpViewModel = viewModel()){

    // TODO: Create NavController
    var navController = rememberNavController()
    var canNavigateBack by remember { mutableStateOf(false) }
    var isGettingStarted by remember { mutableStateOf(false) }
    // TODO: Get current back stack entry

    // TODO: Get the name of the current screen
    // TODO: add NavHost
    Scaffold(
        topBar = {
            TopBar(
                isGettingStarted = isGettingStarted,
                canNavigateBack = canNavigateBack,
                navigateUp = {}
            )
        }
    ) { innerPadding ->
        //val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = NavigationRoute.Onboarding.name,
            modifier = modifier.padding(innerPadding)
        ){

            composable(route = NavigationRoute.Onboarding.name){
                isGettingStarted = true
                canNavigateBack = false
                OnBoardingScreen(
                    onNextButtonClicked = {
                        navController.navigate(NavigationRoute.GetStarted.name)
                    }
                )
            }

            composable(route = NavigationRoute.GetStarted.name) {
                isGettingStarted = true
                canNavigateBack = false
                GettingStartedScreen(
                    onGetStartedButtonClicked = {
                        navController.navigate(NavigationRoute.SignupForm.name)
                    },
                    onLoginButtonClicked = {
                        navController.navigate(NavigationRoute.SignupForm.name)}
                )
            }

            composable(route = NavigationRoute.SignupForm.name){
                isGettingStarted = false
                canNavigateBack = true
                SignupFormScreen()
            }

        }
    }
}

