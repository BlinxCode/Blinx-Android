package com.android.blinxapp.domain.usecase

import com.android.blinxapp.domain.repository.wallet.WalletRepository
import com.android.blinxapp.domain.repository.wallet.createPlaidLinkResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateLinkUseCase @Inject constructor(private val walletRepository: WalletRepository){
    suspend operator fun invoke (): createPlaidLinkResponse = walletRepository.createPlaidLink()
}