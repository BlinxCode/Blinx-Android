package com.android.blinxapp.ui.feature.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.blinxapp.core.RequestState.*
import com.android.blinxapp.domain.model.UserDTO
import com.android.blinxapp.domain.repository.GetUserProfileResponse
import com.android.blinxapp.domain.repository.PinVerificationResponse
import com.android.blinxapp.domain.repository.ProfileRepository
import com.android.blinxapp.domain.repository.SignOutResponse
import com.android.blinxapp.domain.repository.UpdateUserPinResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: ProfileRepository
): ViewModel() {
    val displayName get() = repo.displayName
    val photoUrl get() = repo.photoUrl
    var userProfile by mutableStateOf(UserDTO()) // Assuming UserDTO has a default constructor
    private var signOutResponse by mutableStateOf<SignOutResponse>(Success(false))
    private var updateUserProfileResponse by mutableStateOf<UpdateUserPinResponse>(Success(false))
    private var pinVerificationResponse by mutableStateOf<PinVerificationResponse>(Success(false))
    var getUserProfileResponse by mutableStateOf<GetUserProfileResponse>(Success(UserDTO()))
        private set

    fun signOut() = viewModelScope.launch {
        signOutResponse = Loading
        signOutResponse = repo.signOut()
    }

    init {
        getUserProfileInDB()
    }

    // Fetch user profile asynchronously
    fun getUserProfileInDB() = viewModelScope.launch(Dispatchers.IO){
        getUserProfileResponse = Loading
        getUserProfileResponse = repo.getUserProfile()
    }

    fun updateUserProfile(pin: String) = viewModelScope.launch(Dispatchers.IO)  {
        userProfile.userPin = pin
        updateUserProfileResponse = Loading
        updateUserProfileResponse = repo.updateUserPin(userProfile)
    }

    fun pinVerification(savedPin: String, inputPin: String): PinVerificationResponse {
        return repo.pinVerification(savedPin, inputPin)
    }
}