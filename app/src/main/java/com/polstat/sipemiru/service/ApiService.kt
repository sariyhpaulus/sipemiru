package com.polstat.sipemiru.service

import com.polstat.sipemiru.request.LoginRequest
import com.polstat.sipemiru.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    @FormUrlEncoded
    fun login(@Body loginRequest: LoginRequest): LoginResponse

}