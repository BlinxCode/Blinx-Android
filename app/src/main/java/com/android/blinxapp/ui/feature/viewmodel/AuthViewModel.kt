package com.android.blinxapp.ui.feature.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.blinxapp.core.RequestState
import com.android.blinxapp.domain.repository.AuthRepository
import com.android.blinxapp.domain.repository.AuthCredentialResponse
import com.android.blinxapp.domain.repository.SignInWithGoogleResponse
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {

    var loadingState = mutableStateOf(false)
    fun setLoading(loading: Boolean){
        loadingState.value = loading
    }

    val isUserAuthenticated get() = repo.isUserAuthenticatedInFirebase

    var credentialManagerSignInResponse by mutableStateOf<AuthCredentialResponse>(RequestState.Success(null))
        private set
    var signInWithGoogleResponse by mutableStateOf<SignInWithGoogleResponse>(RequestState.Success(false))
        private set

    fun credentialManagerSignIn() =
        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                    credentialManagerSignInResponse = RequestState.Loading
                    credentialManagerSignInResponse = repo.credentialManagerWithGoogle()
                }
        }

    fun signInWithGoogle(googleCredential: AuthCredential) = viewModelScope.launch {
        Log.d("signInWithGoogle","Checking Firebase Login")
        signInWithGoogleResponse = RequestState.Loading
        signInWithGoogleResponse = repo.firebaseSignInWithGoogle(googleCredential)
    }

    suspend fun handleSignIn(result: GetCredentialResponse) {
// 1
        val credential = result.credential
        // GoogleIdToken credential

        when (credential) {
            is CustomCredential -> {
// 2
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
// 3
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)
// 4
                        val googleIdToken = googleIdTokenCredential.idToken

// 5
                        val authCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
// 6
                        val user = Firebase.auth.signInWithCredential(authCredential).await().user
// 7
                        user?.run {
                            Log.e("TAG", "Update UI State")

                        }
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e("TAG", "Received an invalid google id token response", e)
                    } catch (e: Exception) {
                        Log.e("TAG", "Unexpected error")
                    }
                }
            }

            else -> {
                // Catch any unrecognized credential type here.
                Log.e("TAG", "Unexpected type of credential")
            }
        }
    }
}