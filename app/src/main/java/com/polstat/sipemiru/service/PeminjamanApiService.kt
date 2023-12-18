package com.polstat.sipemiru.service

import com.polstat.sipemiru.model.AddPeminjamanForm
import com.polstat.sipemiru.request.PeminjamanRequest
import com.polstat.sipemiru.response.PeminjamanResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface PeminjamanApiService {
    @POST("/peminjamans")
    suspend fun addPeminjaman(@Header("Authorization") token: String, @Body peminjaman: AddPeminjamanForm): PeminjamanResponse
}