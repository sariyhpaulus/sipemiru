package com.polstat.sipemiru.model

import kotlinx.serialization.Serializable

@Serializable
data class User (
    var userId: Int,
    var email: String,
    var firstName: String,
    var lastName: String,
    var password: String,
    var roles: List<Role>?
)