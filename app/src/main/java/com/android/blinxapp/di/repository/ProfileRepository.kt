package com.android.blinxapp.di.repository


import com.android.blinxapp.core.RequestState

typealias SignOutResponse = RequestState<Boolean>
typealias RevokeAccessResponse = RequestState<Boolean>

interface ProfileRepository {
    val displayName: String
    val photoUrl: String

    suspend fun signOut(): SignOutResponse

    suspend fun revokeAccess(): RevokeAccessResponse
}