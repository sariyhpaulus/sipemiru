package com.polstat.sipemiru.response

data class LoginResponse(
    val status: Int?,
    val message: String?,
    val data: Data?
)

class Data{
    val email: String = ""
    val accessToken: String = ""
}