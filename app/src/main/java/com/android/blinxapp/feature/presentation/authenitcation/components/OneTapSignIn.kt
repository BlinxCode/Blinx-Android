package com.android.blinxapp.feature.presentation.authenitcation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.blinxapp.core.RequestState
import com.android.blinxapp.feature.presentation.components.ProgressBar
import com.android.blinxapp.feature.presentation.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult

@Composable
fun OneTapSignIn(
    viewModel: AuthViewModel = hiltViewModel(),
    launch: (result: BeginSignInResult) -> Unit
) {
    when(val oneTapSignInResponse = viewModel.oneTapSignInResponse) {
        is RequestState.Loading -> ProgressBar()
        is RequestState.Success -> oneTapSignInResponse.data?.let {
            LaunchedEffect(it) {
                launch(it)
            }
        }
        is RequestState.Error -> LaunchedEffect(Unit) {
            print(oneTapSignInResponse.error)
        }
    }
}