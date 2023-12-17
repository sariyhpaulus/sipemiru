package com.polstat.sipemiru.response


data class ProfileResponse(
    val status: Int?,
    val message: String?,
    val data: DataProfile?
)

class DataProfile{
    val email: String = ""
    val roles: List<String> = emptyList()
    val firstName: String = ""
    val lastName: String = ""
}