package com.android.blinxapp.feature.presentation.authenitcation.ui.login

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.blinxapp.feature.presentation.authenitcation.components.OneTapSignIn
import com.android.blinxapp.feature.presentation.authenitcation.components.SignInWithGoogle
import com.android.blinxapp.feature.presentation.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
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

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        content = {
            ContentWithMessageBar(messageBarState = messageBarState) {
                AuthenticationContent(
                    loadingState = viewModel.loadingState, onButtonClicked = {
                        viewModel.setLoading(true)
                        viewModel.oneTapSignIn()
                    })
            }
        }
    )

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            try {
                messageBarState.addSuccess("Successfully Authenticated")
                viewModel.setLoading(false)

                val credentials = viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                val googleIdToken = credentials.googleIdToken
                val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
                viewModel.signInWithGoogle(googleCredentials)
            } catch (it: ApiException) {
                messageBarState.addError(it)
                viewModel.setLoading(false)
                print(it)
            }
        }
    }

    fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }

    OneTapSignIn(
        launch = {
            launch(it)
        }
    )

    SignInWithGoogle(
        navigateToHomeScreen = { signedIn ->
            if (signedIn) {
                navigateToPin()
            }
        }
    )
}