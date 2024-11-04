package com.android.blinxapp.data.repository.wallet

import android.util.Log
import com.android.blinxapp.core.Constants.PLAID
import com.android.blinxapp.core.RequestState
import com.android.blinxapp.domain.repository.wallet.WalletRepository
import com.android.blinxapp.domain.repository.wallet.createPlaidLinkResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : WalletRepository {
    override suspend fun createPlaidLink(): createPlaidLinkResponse {
        return try {
            val linkData = hashMapOf("createLink" to "Yes")
            auth.currentUser?.let { firebaseUser ->
                db.collection("plaid")
                    .document(firebaseUser.uid)
                    .set(linkData)
                    .await()
            }

            // Wait for 2 seconds to ensure the data is saved and processed
            delay(5000L)

            // Retrieve the Plaid link token from Firestore
            val plaidLinkToken = auth.currentUser?.let { firebaseUser ->
                val snapshot = db.collection(PLAID).document(firebaseUser.uid).get().await()
                if (snapshot.exists()) {
                    val data = snapshot.data
                    val token = data?.get("linkToken") as? String
                    Log.d("getToken", "Plaid link token: $token")
                    token
                } else {
                    Log.d("getToken", "No document found for user ID: ${firebaseUser.uid}")
                    null
                }
            }

            plaidLinkToken?.let {
                RequestState.Success(it) // Return the token if found
            } ?: RequestState.Error(Exception("Plaid link token not found"))
        } catch (e: Exception) {
            RequestState.Error(e)
        }
    }
}
