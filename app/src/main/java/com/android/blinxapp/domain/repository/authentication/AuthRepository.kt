package com.android.blinxapp.domain.repository.authentication

import com.google.firebase.auth.AuthCredential
import com.android.blinxapp.core.RequestState

typealias AuthCredentialResponse = RequestState<AuthCredential>
typealias SignInWithGoogleResponse = RequestState<Boolean>

interface AuthRepository {
    val isUserAuthenticatedInFirebase: Boolean

    suspend fun credentialManagerWithGoogle(): AuthCredentialResponse

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): SignInWithGoogleResponse

}