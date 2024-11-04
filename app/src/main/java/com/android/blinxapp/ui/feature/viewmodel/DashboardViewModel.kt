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

    private val _plaidLinkUiState = MutableStateFlow<createPlaidLinkResponse>(RequestState.Loading)
    val plaidLinkUiState = _plaidLinkUiState.asStateFlow()

    fun createPlaidLink(){
        viewModelScope.launch(dispatchProvider.io) {
            _plaidLinkUiState.value = RequestState.Loading // Set loading state immediately

            when (val result = createLinkUseCase.invoke()){
                is RequestState.Error -> withContext(Dispatchers.Main ){
                    _plaidLinkUiState.value = RequestState.Error(result.error) }
                RequestState.Loading ->  RequestState.Loading
                is RequestState.Success -> {
                    _plaidLinkUiState.value = RequestState.Success(result.data)
                }
            }
        }
    }
}