package com.android.blinxapp.domain.repository


import com.android.blinxapp.core.RequestState
import com.android.blinxapp.domain.model.UserDTO

typealias SignOutResponse = RequestState<Boolean>
typealias UpdateUserPinResponse = RequestState<Boolean>
typealias GetUserProfileResponse = RequestState<UserDTO>
typealias PinVerificationResponse = RequestState<Boolean>

interface ProfileRepository {
    val displayName: String
    val photoUrl: String

    suspend fun signOut(): SignOutResponse

    suspend fun updateUserPin(user: UserDTO): UpdateUserPinResponse
    suspend fun getUserProfile(): GetUserProfileResponse


    fun pinVerification(savedPin: String, inputPin: String): PinVerificationResponse
}