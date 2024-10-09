package com.android.blinxapp.ui.feature.presentation.authenitcation.ui.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.blinxapp.ui.feature.presentation.authenitcation.components.CredentialManagerLogin
import com.android.blinxapp.ui.feature.presentation.authenitcation.components.SignInWithGoogle
import com.android.blinxapp.ui.feature.viewmodel.AuthViewModel
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.MessageBarState
import com.stevdzasan.messagebar.rememberMessageBarState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthenticationScreen(
    messageBarState: MessageBarState = rememberMessageBarState(),
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToPin: () -> Unit
){

    val startAddAccountIntentLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            // Once the account has been added, do sign in again.
            viewModel.setLoading(true)
            viewModel.credentialManagerSignIn()
        }



    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        content = {
            ContentWithMessageBar(messageBarState = messageBarState) {
                AuthenticationContent(
                    loadingState = viewModel.loadingState, onButtonClicked = {
                        viewModel.setLoading(true)
                        viewModel.credentialManagerSignIn()
                    })
            }
        }
    )

    fun launch(googleCredentials: AuthCredential) {
        try {
            messageBarState.addSuccess("Successfully Authenticated")
            viewModel.setLoading(false)
            viewModel.signInWithGoogle(googleCredentials)
        } catch (it: ApiException) {
            Log.d("credentialsData", it.toString())
            messageBarState.addError(it)
            viewModel.setLoading(false)
            print(it)
        }
    }

    CredentialManagerLogin(
        messageBarState = messageBarState,
        launch = {
            launch(it)
        },
        startAddAccountIntentLauncher = startAddAccountIntentLauncher
    )

    SignInWithGoogle(
        navigateToHomeScreen = { signedIn ->
            if (signedIn) {
                navigateToPin()
            }
        }
    )
}