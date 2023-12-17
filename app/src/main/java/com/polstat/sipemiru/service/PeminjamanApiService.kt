package com.polstat.sipemiru.service

import com.polstat.sipemiru.request.PeminjamanRequest
import com.polstat.sipemiru.response.PeminjamanResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface PeminjamanApiService {
    @Headers("Authorization:"+SessionManager.USER_TOKEN)
    @POST("peminjamans")
    suspend fun peminjamans(@Body PeminjamanRequest: PeminjamanRequest): PeminjamanResponse
}