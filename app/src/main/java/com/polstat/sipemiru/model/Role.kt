package com.polstat.sipemiru.model

import kotlinx.serialization.Serializable

@Serializable
data class Role (
    val role_id: Long?,
    val name: String
)