package com.android.blinxapp.di

import android.content.Context
import com.android.blinxapp.core.DispatchProvider
import com.android.blinxapp.data.repository.authentication.AuthRepositoryImpl
import com.android.blinxapp.data.repository.authentication.GoogleIdOptions
import com.android.blinxapp.data.repository.authentication.ProfileRepositoryImpl
import com.android.blinxapp.data.repository.wallet.WalletRepositoryImpl
import com.android.blinxapp.domain.repository.authentication.AuthRepository
import com.android.blinxapp.domain.repository.ProfileRepository
import com.android.blinxapp.domain.repository.wallet.WalletRepository
import com.android.blinxapp.domain.usecase.CreateLinkUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {
    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore


    @Provides
    fun provideGoogleIdOption(@ApplicationContext context: Context): GoogleIdOptions =
        GoogleIdOptions(context = context)


    @Provides
    fun provideAuthRepository(
        @ApplicationContext context: Context,
        auth: FirebaseAuth,
        db: FirebaseFirestore,
        googleIdOptions: GoogleIdOptions,

        ): AuthRepository = AuthRepositoryImpl(
        activityContext = context,
        auth = auth,
        db = db,
        googleIdOptions = googleIdOptions,
    )

    @Provides
    fun provideProfileRepository(
        auth: FirebaseAuth,
        db: FirebaseFirestore
    ): ProfileRepository = ProfileRepositoryImpl(
        auth = auth,
        db = db
    )

    @Provides
    fun provideWalletRepository(
        auth: FirebaseAuth,
        db: FirebaseFirestore
    ): WalletRepository = WalletRepositoryImpl(auth = auth, db = db)


    @Provides
    fun provideCreateLinkUseCase(
        walletRepository: WalletRepository
    ): CreateLinkUseCase = CreateLinkUseCase(walletRepository)


    @Provides
    fun provideDispatchers(): DispatchProvider = object : DispatchProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}