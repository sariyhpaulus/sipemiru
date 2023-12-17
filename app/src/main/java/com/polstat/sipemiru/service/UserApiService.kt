package com.polstat.sipemiru.service

import com.polstat.sipemiru.model.LoginRequest
import com.polstat.sipemiru.model.LoginResponse
import com.polstat.sipemiru.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApiService {
   @GET("/users/profile")
   suspend fun showProfile(@Header("Authorization") token: String): UserResponse

}