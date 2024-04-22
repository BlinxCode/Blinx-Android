package com.android.blinxapp.feature.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.blinxapp.core.RequestState.*
import com.android.blinxapp.domain.model.UserDTO
import com.android.blinxapp.domain.repository.GetUserProfileResponse
import com.android.blinxapp.domain.repository.ProfileRepository
import com.android.blinxapp.domain.repository.RevokeAccessResponse
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
    var signOutResponse by mutableStateOf<SignOutResponse>(Success(false))
        private set
     var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Success(false))
         private set
    var updateUserProfileResponse by mutableStateOf<UpdateUserPinResponse>(Success(false))
        private set
    var getUserProfileResponse by mutableStateOf<GetUserProfileResponse>(Success(UserDTO()))
        private set

    fun signOut() = viewModelScope.launch {
        signOutResponse = Loading
        signOutResponse = repo.signOut()
    }

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Loading
        revokeAccessResponse = repo.revokeAccess()
    }

    init {
      //  getUserProfileInDB()
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

}