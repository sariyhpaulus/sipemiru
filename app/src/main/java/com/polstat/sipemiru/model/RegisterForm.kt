package com.polstat.sipemiru.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterForm(
    val role_id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)