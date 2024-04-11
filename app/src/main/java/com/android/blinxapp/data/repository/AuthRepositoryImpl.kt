package com.android.blinxapp.data.repository

import android.util.Log
import com.android.blinxapp.core.Constants.SIGN_IN_REQUEST
import com.android.blinxapp.core.Constants.SIGN_UP_REQUEST
import com.android.blinxapp.core.Constants.USERS
import com.android.blinxapp.core.RequestState
import com.android.blinxapp.domain.model.User
import com.android.blinxapp.domain.repository.AuthRepository
import com.android.blinxapp.domain.repository.OneTapSignInResponse
import com.android.blinxapp.domain.repository.SignInWithGoogleResponse
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
            Log.d("signInWithGoogle", "Checking Firebase Auth")

            val authResult = auth.signInWithCredential(googleCredential).await()
            Log.d("signInWithGoogleUser", "${authResult.user}")
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                addUserToFirestore()
            }
            RequestState.Success(true)
        } catch (e: Exception) {
            Log.d("signInWithGoogleError", "${e.message}")
            RequestState.Error(e)
        }
    }

    private suspend fun addUserToFirestore() {
        auth.currentUser?.apply {
            val user = toUser()
            db.collection(USERS).document(uid).set(user).await()
        }
    }

    private fun FirebaseUser.toUser(): User {
        return User(
            displayName = displayName,
            email = email,
            photoUrl = photoUrl.toString(),
            createdAt = FieldValue.serverTimestamp(),
        )
    }
}
