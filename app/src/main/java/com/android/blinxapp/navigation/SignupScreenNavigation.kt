package com.android.blinxapp.navigation

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.blinxapp.authenitcation.ui.onboarding.presentation.OnBoardingScreen
import com.android.blinxapp.authenitcation.ui.signup.aboutyou.AboutYouScreen
import com.android.blinxapp.authenitcation.ui.signup.email.ConfirmEmailScreen
import com.android.blinxapp.authenitcation.ui.signup.form.SignupFormScreen
import com.android.blinxapp.authenitcation.ui.get_started.GettingStartedScreen
import com.android.blinxapp.authenitcation.ui.login.LoginScreen
import com.android.blinxapp.authenitcation.ui.pin.PinSetupScreen
import com.android.blinxapp.authenitcation.ui.signup.phone.ConfirmPhoneNumberScreen
import com.android.blinxapp.authenitcation.ui.signup.phone.PhoneNumberScreen
import com.android.blinxapp.authenitcation.ui.sucess.SuccessScreen
import com.android.blinxapp.authenitcation.ui.viewmodel.SignUpViewModel
import com.android.blinxapp.common.toolbar.OnBoardingTopbar
import com.android.blinxapp.dashboard.ui.presentation.navigation.DashboardNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupNavigation(context: Context, viewModel: SignUpViewModel = viewModel()){

    // TODO: Create NavController
    var navController = rememberNavController()
    var canNavigateBack by remember { mutableStateOf(false) }
    var isGettingStarted by remember { mutableStateOf(false) }
    var isDashboard by remember { mutableStateOf(false) }
    var modifier: Modifier = Modifier

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    // TODO: Get current back stack entry

    // TODO: Get the name of the current screen
    // TODO: add NavHost
    Scaffold(
        topBar = {

            if (!isDashboard){
                OnBoardingTopbar(
                    isGettingStarted = isGettingStarted,
                    canNavigateBack = canNavigateBack,
                    navigateUp = {}
                )
            }
        }
    ) { innerPadding ->
        //val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = AuthNavigationRoute.Onboarding.name,
            modifier = modifier.padding(innerPadding)
        ){

            composable(route = AuthNavigationRoute.Onboarding.name){
                isDashboard = false
                isGettingStarted = true
                canNavigateBack = false
                OnBoardingScreen(
                    onNextButtonClicked = {
                        navController.navigate(AuthNavigationRoute.GetStarted.name)
                    }
                )
            }

            composable(route = AuthNavigationRoute.GetStarted.name) {
                isDashboard = false
                isGettingStarted = true
                canNavigateBack = false
                GettingStartedScreen(
                    onGetStartedButtonClicked = {
                        navController.navigate(AuthNavigationRoute.SignupForm.name)
                    },
                    onLoginButtonClicked = {
                        navController.navigate(AuthNavigationRoute.Login.name)}
                )
            }

            composable(route = AuthNavigationRoute.SignupForm.name){
                isGettingStarted = false
                isDashboard = false
                canNavigateBack = true
                SignupFormScreen(
                    onSignupButtonClicked = {
                        navController.navigate(AuthNavigationRoute.ConfirmEmail.name)}
                )
            }
            composable(route = AuthNavigationRoute.ConfirmEmail.name){
                isGettingStarted = false
                isDashboard = false
                canNavigateBack = true
                ConfirmEmailScreen(
                    onEmailConfirmButtonClicked ={navController.navigate(AuthNavigationRoute.PhoneNumber.name) },
                    onEmailConfirmResendButtonClicked={}
                )
            }
            composable(route = AuthNavigationRoute.PhoneNumber.name){
                isGettingStarted = false
                isDashboard = false
                canNavigateBack = true
                PhoneNumberScreen(
                    onInputPhoneNumberButtonClicked = {
                        navController.navigate((AuthNavigationRoute.ConfirmPhoneNumber.name))
                    }
                )
            }
            composable(route = AuthNavigationRoute.ConfirmPhoneNumber.name){
                isGettingStarted = false
                isDashboard = false
                canNavigateBack = true
                ConfirmPhoneNumberScreen(
                    onPhoneConfirmButtonClicked ={
                        navController.navigate((AuthNavigationRoute.AboutYou.name))
                    },
                    onPhoneConfirmResendButtonClicked ={}
                )
            }
            composable(route = AuthNavigationRoute.AboutYou.name){
                isGettingStarted = false
                isDashboard = false
                canNavigateBack = true
                AboutYouScreen(
                    onProceedClicked ={
                        navController.navigate(AuthNavigationRoute.Success.name)
                    }
                )
            }
            composable(route = AuthNavigationRoute.Success.name){
                isGettingStarted = true
                isDashboard = false
                canNavigateBack = false
                SuccessScreen  {
                    navController.navigate(AuthNavigationRoute.Login.name) }
            }

            composable(route = AuthNavigationRoute.Login.name){
                isGettingStarted = false
                isDashboard = false
                canNavigateBack = true
                LoginScreen {
                    navController.navigate(AuthNavigationRoute.PinSetup.name)
                }
            }

            composable(route = AuthNavigationRoute.PinSetup.name) {
                isGettingStarted = false
                isDashboard = false
                canNavigateBack = false
                PinSetupScreen (
                    onProceedClicked ={
                        navController.navigate(AuthNavigationRoute.Dashboard.name)
                    },
                    context = context
                )
            }
            composable(route = AuthNavigationRoute.Dashboard.name) {
                isDashboard = true
                canNavigateBack = false
                DashboardNavigation ()
            }

        }
    }
}



