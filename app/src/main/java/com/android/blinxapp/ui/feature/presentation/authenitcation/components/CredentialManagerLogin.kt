package com.android.blinxapp.ui.feature.presentation.authenitcation.components

import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.blinxapp.core.RequestState
import com.android.blinxapp.ui.feature.viewmodel.AuthViewModel
import com.google.firebase.auth.AuthCredential
import com.stevdzasan.messagebar.MessageBarState
import java.lang.Exception

@Composable
fun CredentialManagerLogin(
    viewModel: AuthViewModel = hiltViewModel(),
    launch: (result: AuthCredential) -> Unit,
    messageBarState: MessageBarState,
    startAddAccountIntentLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>?,
) {
    when(val credManagerSignInResponse = viewModel.credentialManagerSignInResponse) {
        is RequestState.Loading -> {}
        is RequestState.Success -> credManagerSignInResponse.data?.let {
            Log.d("CredentialManagerSignSignIn", it.toString())
            LaunchedEffect(it) {
                launch(it)
            }
        }
        is RequestState.Error -> LaunchedEffect(Unit) {
            messageBarState.addError(Exception(credManagerSignInResponse.error.message))
            viewModel.setLoading(false)
            startAddAccountIntentLauncher?.launch(getAddGoogleAccountIntent())
        }
    }
}
fun getAddGoogleAccountIntent(): Intent {
    val intent = Intent(Settings.ACTION_ADD_ACCOUNT)
    intent.putExtra(Settings.EXTRA_ACCOUNT_TYPES, arrayOf("com.google"))
    return intent
}