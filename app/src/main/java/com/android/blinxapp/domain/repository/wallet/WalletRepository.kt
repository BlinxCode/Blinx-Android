package com.android.blinxapp.domain.repository.wallet

import com.android.blinxapp.core.RequestState

typealias createPlaidLinkResponse = RequestState<String>

interface WalletRepository {
    suspend fun createPlaidLink(): createPlaidLinkResponse
}