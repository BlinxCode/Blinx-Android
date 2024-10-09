package com.android.blinxapp.domain.repository

import com.android.blinxapp.core.RequestState
import com.google.firebase.auth.AuthCredential

typealias AuthCredentialResponse = RequestState<AuthCredential>
typealias SignInWithGoogleResponse = RequestState<Boolean>
interface AuthRepository {
    val isUserAuthenticatedInFirebase: Boolean

    suspend fun credentialManagerWithGoogle(): AuthCredentialResponse

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): SignInWithGoogleResponse

}