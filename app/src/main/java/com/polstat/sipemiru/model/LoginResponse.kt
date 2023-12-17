package com.polstat.sipemiru.model

data class LoginResponse(
    val status: Int?,
    val message: String?,
    val data: Data?
)

class Data{
    val email: String = ""
    val accessToken: String = ""
}