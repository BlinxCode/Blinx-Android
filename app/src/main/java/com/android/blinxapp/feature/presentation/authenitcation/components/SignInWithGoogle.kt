package com.android.blinxapp.feature.presentation.authenitcation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.blinxapp.core.RequestState.*
import com.android.blinxapp.feature.presentation.components.ProgressBar
import com.android.blinxapp.feature.presentation.viewmodel.AuthViewModel

@Composable
fun SignInWithGoogle(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToHomeScreen: (signedIn: Boolean) -> Unit
) {
    when(val signInWithGoogleResponse = viewModel.signInWithGoogleResponse) {
        is Loading -> ProgressBar()
        is Success -> signInWithGoogleResponse.data?.let { signedIn ->
            LaunchedEffect(signedIn) {
                navigateToHomeScreen(signedIn)
            }
        }
        is Error -> LaunchedEffect(Unit) {
            print(signInWithGoogleResponse.error)
        }
    }
}