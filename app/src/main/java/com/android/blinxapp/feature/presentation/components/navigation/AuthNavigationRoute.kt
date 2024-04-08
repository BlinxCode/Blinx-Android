package com.android.blinxapp.feature.presentation.components.navigation

import com.android.blinxapp.core.Constants.AUTH_SCREEN
import com.android.blinxapp.core.Constants.PIN_SCREEN

enum class AuthNavigationRoute {
    Onboarding,
    Login,
    PhoneNumber,
    ConfirmPhoneNumber,
    AboutYou,
    Success,
}


sealed class Screen(val route: String) {
    data object AuthScreen: Screen(AUTH_SCREEN)
    data object PinScreen: Screen(PIN_SCREEN)
}