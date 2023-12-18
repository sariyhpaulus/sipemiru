package com.polstat.sipemiru.model

import kotlinx.serialization.Serializable

@Serializable
data class EditProfileForm(
    val firstName: String,
    val lastName: String,
    val email: String
)