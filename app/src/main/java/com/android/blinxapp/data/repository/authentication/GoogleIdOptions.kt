package com.android.blinxapp.data.repository

import android.content.Context
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import java.security.MessageDigest
import java.util.UUID
import javax.inject.Singleton
import com.android.blinxapp.BuildConfig

@Singleton
class GoogleIdOptions(val context: Context) {

    fun googleSignInOptions():
            GetGoogleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false) // true - check if the user has any accounts that have previously been used to sign in to the app
        .setServerClientId(BuildConfig.SERVER_CLIENT_ID)
        .setAutoSelectEnabled(true) // true- Enable automatic sign-in for returning users
        .setNonce(getHashNode())
        .build()

    private fun getHashNode():String {
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        val hashedNonce = digest.fold("") { str, it ->
            str + "%02x".format(it)
        }
        return hashedNonce
    }
}