package com.android.blinxapp.ui.feature.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.blinxapp.core.RequestState
import com.android.blinxapp.domain.repository.authentication.AuthRepository
import com.android.blinxapp.domain.repository.authentication.SignInWithGoogleResponse
import com.android.blinxapp.domain.repository.authentication.AuthCredentialResponse
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

}