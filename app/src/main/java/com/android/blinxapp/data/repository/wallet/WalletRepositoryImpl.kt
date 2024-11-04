package com.android.blinxapp.data.repository.wallet

import com.android.blinxapp.core.RequestState
import com.android.blinxapp.domain.repository.wallet.WalletRepository
import com.android.blinxapp.domain.repository.wallet.createPlaidLinkResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore ): WalletRepository {
    override suspend fun createPlaidLink(): createPlaidLinkResponse {

        return try {
            val linkData = hashMapOf(
                "createLink" to "Yes",
            )
            auth.currentUser?.apply {
                db.collection("plaid")
                    .document(uid)
                    .set(linkData)
                    .await()
            }

            RequestState.Success("Plaid Link successfully requested!")
        } catch (e: Exception) {
            RequestState.Error(e)
        }
    }
}