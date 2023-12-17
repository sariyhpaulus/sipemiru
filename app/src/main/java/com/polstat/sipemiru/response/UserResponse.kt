package com.polstat.sipemiru.response

import com.polstat.sipemiru.model.User
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse (
    val status: Int?,
    val message: String?,
    val data: User?
)