package com.android.blinxapp.navigation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
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
import com.android.blinxapp.dashboard.ui.presentation.navigation.DashboardBottomNav
import com.android.blinxapp.dashboard.ui.presentation.navigation.DashboardNavigationRoute
import com.android.blinxapp.dashboard.ui.presentation.navigation.DashboardTopBar
import com.android.blinxapp.dashboard.ui.presentation.navigation.dashboardNavigation


@Composable
fun SignupNavigation(context: Context, viewModel: SignUpViewModel = viewModel()){

    // TODO: Create NavController
    var navController = rememberNavController()

    var canNavigateBack = remember { mutableStateOf(false) }
    var isGettingStarted by remember { mutableStateOf(false) }
    val isDashboard = remember { mutableStateOf(false) }
    var modifier: Modifier = Modifier
    val topBarTitle = remember { mutableStateOf("") }

    val destinationStr = remember { mutableStateOf("Onboarding") }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    // TODO: Get current back stack entry

    // TODO: Get the name of the current screen
    // TODO: add NavHost

    val myPadding = PaddingValues(
        start = 16.dp,
        end = 16.dp
    )
    val zeroPaddingValues = PaddingValues(
        start = 0.dp,
        end = 0.dp
    )
    Scaffold(
        topBar = {

                DashboardTopBar( canNavigateBack.value,topBarTitle, navigateUp = {}, isDashboard.value,
                    isGettingStarted = isGettingStarted,)
        },
        bottomBar = {
            if (isDashboard.value){
                DashboardBottomNav(isDashboard, currentRoute, navController)
            }
        },

    ) { innerPadding ->


        //val uiState by viewModel.uiState.collectAsState()
        Box(modifier = modifier.fillMaxSize()
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.primary)){
            Box(modifier = modifier.padding(if (!isGettingStarted){myPadding} else {zeroPaddingValues})
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)) {

                OnBoardingNavHost(
                    navController,
                    isDashboard,
                    isGettingStarted,
                    canNavigateBack,
                    context,
                    topBarTitle,
                    destinationStr
                )
            }
        }

    }
}

@Composable
fun OnBoardingNavHost(
    navController: NavHostController,
    isDashboard: MutableState<Boolean>,
    isGettingStarted: Boolean,
    canNavigateBack: MutableState<Boolean>,
    context: Context,
    topBarTitle: MutableState<String>,
    destinationStr: MutableState<String>
) {
    var isGettingStarted1 = isGettingStarted

    //NavHost(navController, startDestination = DashboardNavigationRoute.HOME.name) {
    NavHost(
        navController = navController,
        startDestination = destinationStr.value
    ) {


        composable(route =  AuthNavigationRoute.Onboarding.name) {
            isDashboard.value = false
            isGettingStarted1 = true
            canNavigateBack.value = false
            OnBoardingScreen(
                onNextButtonClicked = {
                    navController.navigate(AuthNavigationRoute.GetStarted.name)
                }
            )
        }

        composable(route = AuthNavigationRoute.GetStarted.name) {
            isDashboard.value = false
            isGettingStarted1 = true
            canNavigateBack.value = false
            GettingStartedScreen(
                onGetStartedButtonClicked = {
                    navController.navigate(AuthNavigationRoute.SignupForm.name)
                },
                onLoginButtonClicked = {
                    navController.navigate(AuthNavigationRoute.Login.name)
                }
            )
        }

        composable(route = AuthNavigationRoute.SignupForm.name) {
            isGettingStarted1 = false
            isDashboard.value = false
            canNavigateBack.value = true
            SignupFormScreen(
                onSignupButtonClicked = {
                    navController.navigate(AuthNavigationRoute.ConfirmEmail.name)
                }
            )
        }
        composable(route = AuthNavigationRoute.ConfirmEmail.name) {
            isGettingStarted1 = false
            isDashboard.value = false
            canNavigateBack.value = true
            ConfirmEmailScreen(
                onEmailConfirmButtonClicked = { navController.navigate(AuthNavigationRoute.PhoneNumber.name) },
                onEmailConfirmResendButtonClicked = {}
            )
        }
        composable(route = AuthNavigationRoute.PhoneNumber.name) {
            isGettingStarted1 = false
            isDashboard.value = false
            canNavigateBack.value = true
            PhoneNumberScreen(
                onInputPhoneNumberButtonClicked = {
                    navController.navigate((AuthNavigationRoute.ConfirmPhoneNumber.name))
                }
            )
        }
        composable(route = AuthNavigationRoute.ConfirmPhoneNumber.name) {
            isGettingStarted1 = false
            isDashboard.value = false
            canNavigateBack.value = true
            ConfirmPhoneNumberScreen(
                onPhoneConfirmButtonClicked = {
                    navController.navigate((AuthNavigationRoute.AboutYou.name))
                },
                onPhoneConfirmResendButtonClicked = {}
            )
        }
        composable(route = AuthNavigationRoute.AboutYou.name) {
            isGettingStarted1 = false
            isDashboard.value = false
            canNavigateBack.value = true
            AboutYouScreen(
                onProceedClicked = {
                    navController.navigate(AuthNavigationRoute.Success.name)
                }
            )
        }
        composable(route = AuthNavigationRoute.Success.name) {
            isGettingStarted1 = true
            isDashboard.value = false
            canNavigateBack.value = false
            SuccessScreen {
                navController.navigate(AuthNavigationRoute.Login.name)
            }
        }

        composable(route = AuthNavigationRoute.Login.name) {
            isGettingStarted1 = false
            isDashboard.value = false
            canNavigateBack.value = true
            LoginScreen {
                navController.navigate(AuthNavigationRoute.PinSetup.name)
            }
        }

        composable(route = AuthNavigationRoute.PinSetup.name) {
            isGettingStarted1 = false
            isDashboard.value = false
            canNavigateBack.value = false
            PinSetupScreen(
                onProceedClicked = {
                    navController.navigate(DashboardNavigationRoute.HOME.name){
                        launchSingleTop = true
                        popUpTo(AuthNavigationRoute.PinSetup.name) {
                            inclusive = true
                        }

                    }

                },
                context = context
            )
        }

        dashboardNavigation(
            navController = navController, canNavigateBack = canNavigateBack,
            isDashboard = isDashboard, topBarTitle = topBarTitle
        )
    }
}



