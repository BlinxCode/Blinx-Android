package com.android.blinxapp.data.repository

import com.android.blinxapp.core.Constants.CREATED_AT
import com.android.blinxapp.core.Constants.DISPLAY_NAME
import com.android.blinxapp.core.Constants.EMAIL
import com.android.blinxapp.core.Constants.PHOTO_URL
import com.android.blinxapp.core.Constants.SIGN_IN_REQUEST
import com.android.blinxapp.core.Constants.SIGN_UP_REQUEST
import com.android.blinxapp.core.Constants.USERS
import com.android.blinxapp.core.RequestState
import com.android.blinxapp.di.repository.AuthRepository
import com.android.blinxapp.di.repository.OneTapSignInResponse
import com.android.blinxapp.di.repository.SignInWithGoogleResponse
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
    private val db: FirebaseFirestore
) : AuthRepository {
    override val isUserAuthenticatedInFirebase = auth.currentUser != null

    override suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse {
        return try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            RequestState.Success(signInResult)
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                RequestState.Success(signUpResult)
            } catch (e: Exception) {
                RequestState.Error(e)
            }
        }
    }

    override suspend fun firebaseSignInWithGoogle(
        googleCredential: AuthCredential
    ): SignInWithGoogleResponse {
        return try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                addUserToFirestore()
            }
            RequestState.Success(true)
        } catch (e: Exception) {
            RequestState.Error(e)
        }
    }

    private suspend fun addUserToFirestore() {
        auth.currentUser?.apply {
            val user = toUser()
            db.collection(USERS).document(uid).set(user).await()
        }
    }
}

fun FirebaseUser.toUser() = mapOf(
    DISPLAY_NAME to displayName,
    EMAIL to email,
    PHOTO_URL to photoUrl?.toString(),
    CREATED_AT to FieldValue.serverTimestamp()
)