package com.polstat.sipemiru.service

import com.polstat.sipemiru.model.LoginRequest
import com.polstat.sipemiru.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}