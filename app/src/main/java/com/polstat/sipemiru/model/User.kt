package com.polstat.sipemiru.model

import kotlinx.serialization.Serializable

@Serializable
data class User (
    var email: String,
    var firstName: String,
    var lastName: String,
    var roles: List<String>
)