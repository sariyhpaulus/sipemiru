package com.polstat.sipemiru.service

import com.polstat.sipemiru.response.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileApiService {
    @GET("users/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): ProfileResponse
}