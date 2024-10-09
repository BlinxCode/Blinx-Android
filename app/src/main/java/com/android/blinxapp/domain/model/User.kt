package com.android.blinxapp.domain.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue

data class User(
    var displayName: String?,
    var email: String?,
    var photoUrl: String,
    var createdAt: FieldValue? = null,
    var userPin: String? = null
){
    constructor() : this("", "", "", null, null)

}

@IgnoreExtraProperties
data class UserDTO(
    var displayName: String?="",
    var email: String?="",
    var photoUrl: String? ="",
    var createdAt: Timestamp? = Timestamp.now(),
    var userPin: String? = null
){
    constructor() : this("", "", "", null, null)
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "displayName" to displayName,
            "email" to email,
            "photoUrl" to photoUrl,
            "createdAt" to createdAt,
            "userPin" to userPin,
        )
    }
}