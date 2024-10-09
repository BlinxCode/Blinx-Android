package com.android.blinxapp.ui.feature.presentation.authenitcation.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.blinxapp.core.RequestState.*
import com.android.blinxapp.ui.feature.viewmodel.AuthViewModel

@Composable
fun SignInWithGoogle(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToHomeScreen: (signedIn: Boolean) -> Unit
) {
    when(val signInWithGoogleResponse = viewModel.signInWithGoogleResponse) {
        is Loading -> {
            Log.d("LoadingState", "SignInWithGoogle: Loading")
        }
        is Success -> signInWithGoogleResponse.data?.let { signedIn ->
            LaunchedEffect(signedIn) {
                navigateToHomeScreen(signedIn)
            }
        }
        is Error -> LaunchedEffect(Unit) {
            Log.d("SignInWithGoogle", "SignInWithGoogle: ${signInWithGoogleResponse.error.message}")
        }
    }
}