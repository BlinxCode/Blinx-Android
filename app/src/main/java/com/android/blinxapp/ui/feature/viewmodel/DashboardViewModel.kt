package com.android.blinxapp.ui.feature.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.blinxapp.core.DispatchProvider
import com.android.blinxapp.core.RequestState
import com.android.blinxapp.domain.repository.wallet.createPlaidLinkResponse
import com.android.blinxapp.domain.usecase.CreateLinkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val createLinkUseCase: CreateLinkUseCase,
    private val dispatchProvider: DispatchProvider
): ViewModel() {

    var loadingState = mutableStateOf(false)

    fun setLoading(loading: Boolean){
        loadingState.value = loading
    }

//    private val _plaidLinkUiState = MutableStateFlow<createPlaidLinkResponse>(RequestState.Loading)
//    val plaidLinkUiState = _plaidLinkUiState.asStateFlow()


    fun createPlaidLink(
        onSuccess: () -> Unit,
        onError: (Exception)-> Unit
    ){
        viewModelScope.launch(dispatchProvider.io) {
            when (createLinkUseCase.invoke()){
                is RequestState.Error -> withContext(Dispatchers.Main ){
                    onError(Exception("User is not logged in.")) }
                RequestState.Loading ->  loadingState
                is RequestState.Success -> {
                    withContext(Dispatchers.Main){
                        onSuccess()
                    }
                }
            }
        }
    }

}