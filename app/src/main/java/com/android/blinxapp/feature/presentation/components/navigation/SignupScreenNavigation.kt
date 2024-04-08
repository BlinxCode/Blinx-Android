package com.android.blinxapp.feature.presentation.components.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.android.blinxapp.app.DashboardActivity
import com.android.blinxapp.feature.presentation.authenitcation.ui.login.AuthenticationScreen
import com.android.blinxapp.feature.presentation.authenitcation.ui.onboarding.presentation.OnBoardingScreen
import com.android.blinxapp.feature.presentation.authenitcation.ui.pin.PinSetupScreen
import com.android.blinxapp.feature.presentation.authenitcation.ui.signup.aboutyou.AboutYouScreen
import com.android.blinxapp.feature.presentation.authenitcation.ui.signup.phone.ConfirmPhoneNumberScreen
import com.android.blinxapp.feature.presentation.authenitcation.ui.signup.phone.PhoneNumberScreen
import com.android.blinxapp.feature.presentation.authenitcation.ui.sucess.SuccessScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost


@Composable
fun SignupNavigation(navController: NavHostController){
    // Retrieve the application context
    val context = remember { navController.context }
    // TODO: Create NavController

    val modifier: Modifier = Modifier

    val zeroPaddingValues = PaddingValues(
        start = 0.dp,
        end = 0.dp
    )
    Scaffold{ innerPadding ->

        Box(modifier = modifier.fillMaxSize()
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.primary)){
            Box(modifier = modifier.padding(zeroPaddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)) {

                OnBoardingNavHost(
                    navController,
                    context
                )
            }
        }

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OnBoardingNavHost(
    navController: NavHostController,
    context: Context
) {

    AnimatedNavHost(
        navController = navController,
        startDestination = AuthNavigationRoute.Onboarding.name,
    ) {

        composable(route = AuthNavigationRoute.Onboarding.name) {
            OnBoardingScreen(
                onNextButtonClicked = {
                    navController.navigate(Screen.AuthScreen.route)
                }
            )
        }

        composable(route = Screen.AuthScreen.route) {
            AuthenticationScreen(
                navigateToPin = {
                    navController.navigate(Screen.PinScreen.route) {
                        launchSingleTop = true
                        popUpTo(Screen.PinScreen.route) {
                            inclusive = true
                        }

                    }
                })
        }

        //First Screen
        composable(route = Screen.PinScreen.route) {
            PinSetupScreen(
                onProceedClicked = {
                    val intent = Intent(context, DashboardActivity::class.java)
                    context.startActivity(intent)

                },
                context = context
            )
        }

        composable(route = AuthNavigationRoute.PhoneNumber.name) {
            PhoneNumberScreen(
                onInputPhoneNumberButtonClicked = {
                    navController.navigate((AuthNavigationRoute.ConfirmPhoneNumber.name))
                }
            )
        }
        composable(route = AuthNavigationRoute.ConfirmPhoneNumber.name) {
            ConfirmPhoneNumberScreen(
                onPhoneConfirmButtonClicked = {
                    navController.navigate((AuthNavigationRoute.AboutYou.name))
                },
                onPhoneConfirmResendButtonClicked = {}
            )
        }
        composable(route = AuthNavigationRoute.AboutYou.name) {
            AboutYouScreen(
                onProceedClicked = {
                    navController.navigate(AuthNavigationRoute.Success.name)
                }
            )
        }
        composable(route = AuthNavigationRoute.Success.name) {
            SuccessScreen {
                navController.navigate(AuthNavigationRoute.Login.name)
            }
        }
    }
}



