package com.android.blinxapp.di

import android.content.Context
import com.android.blinxapp.data.repository.AuthRepositoryImpl
import com.android.blinxapp.data.repository.GoogleIdOptions
import com.android.blinxapp.data.repository.ProfileRepositoryImpl
import com.android.blinxapp.domain.repository.AuthRepository
import com.android.blinxapp.domain.repository.ProfileRepository
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

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {
    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore


    @Provides
    fun provideGoogleIdOption(@ApplicationContext context: Context):GoogleIdOptions =
        GoogleIdOptions(context = context)


    @Provides
    fun provideAuthRepository(
        @ApplicationContext context: Context,
        auth: FirebaseAuth,
         db: FirebaseFirestore,
        googleIdOptions: GoogleIdOptions,

    ):AuthRepository = AuthRepositoryImpl(
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
}