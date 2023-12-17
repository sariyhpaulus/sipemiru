package com.polstat.sipemiru.service

import com.polstat.sipemiru.request.RuanganRequest
import com.polstat.sipemiru.response.RuanganResponse
import com.polstat.sipemiru.service.SessionManager.Companion.USER_TOKEN
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface RuanganApiService {
    @Headers("Authorization:"+USER_TOKEN)
    @POST("ruangans")
    suspend fun ruangans(@Body RuanganRequest: RuanganRequest): RuanganResponse
}