package com.polstat.sipemiru.service

import com.polstat.sipemiru.model.AddRuanganForm
import com.polstat.sipemiru.request.RuanganRequest
import com.polstat.sipemiru.response.ListRuanganResponse
import com.polstat.sipemiru.response.RuanganResponse
import com.polstat.sipemiru.service.SessionManager.Companion.USER_TOKEN
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface RuanganApiService {
    @POST("/ruangans")
    suspend fun addRuangan(@Header("Authorization") token: String, @Body ruangan: AddRuanganForm): RuanganResponse

    @GET("/ruangans")
    suspend fun getAllRuangan(@Header("Authorization") token: String): ListRuanganResponse

}