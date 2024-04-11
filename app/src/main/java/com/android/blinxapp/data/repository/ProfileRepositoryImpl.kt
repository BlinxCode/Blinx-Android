package com.android.blinxapp.data.repository

import android.util.Log
import com.android.blinxapp.core.Constants.USERS
import com.android.blinxapp.core.RequestState
import com.android.blinxapp.domain.model.UserDTO
import com.android.blinxapp.domain.repository.GetUserProfileResponse
import com.android.blinxapp.domain.repository.ProfileRepository
import com.android.blinxapp.domain.repository.RevokeAccessResponse
import com.android.blinxapp.domain.repository.SignOutResponse
import com.android.blinxapp.domain.repository.UpdateUserPinResponse
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    private var signInClient: GoogleSignInClient,
    private val db: FirebaseFirestore
) : ProfileRepository {
    override val displayName = auth.currentUser?.displayName.toString()
    override val photoUrl = auth.currentUser?.photoUrl.toString()

    override suspend fun signOut(): SignOutResponse {
        return try {
            oneTapClient.signOut().await()
            auth.signOut()
            RequestState.Success(true)
        } catch (e: Exception) {
            RequestState.Error(e)
        }
    }

    override suspend fun revokeAccess(): RevokeAccessResponse {
        return try {
            auth.currentUser?.apply {
                db.collection(USERS).document(uid).delete().await()
                signInClient.revokeAccess().await()
                oneTapClient.signOut().await()
                delete().await()
            }
            RequestState.Success(true)
        } catch (e: Exception) {
            RequestState.Error(e)
        }
    }

    override suspend fun updateUserPin(user: UserDTO): UpdateUserPinResponse {

        return try {
            val tasksMap = user.toMap()
            auth.currentUser?.apply {
                db.collection(USERS).document(uid).set(tasksMap).await()
            }
            RequestState.Success(true)
        } catch (e: Exception) {
            RequestState.Error(e)
        }
    }

    override suspend fun getUserProfile(): GetUserProfileResponse {
        return try {
            val user = auth.currentUser?.let { firebaseUser ->
                val id = firebaseUser.uid
                val snapshot = db.collection(USERS).document(id).get().await()
                if (snapshot.exists()) {
                    Log.d("getUserProfile", "${snapshot.data}")
                    snapshot.toObject(UserDTO::class.java)
                } else {
                    null // User document not found
                }
            }

            if (user != null) {
                RequestState.Success(user)
            } else {
                // Handle the scenario where the user document does not exist
                Log.d("ProfileRepository", "getUserObject: Error")
                RequestState.Error(Exception("User not found"))
            }
        } catch (e: Exception) {
            Log.d("ProfileRepository", "getUserObject: Error")
            RequestState.Error(e)
        }
    }

}
