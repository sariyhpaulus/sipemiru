package com.polstat.sipemiru.service

import com.polstat.sipemiru.request.LoginRequest
import com.polstat.sipemiru.request.RuanganRequest
import com.polstat.sipemiru.response.LoginResponse
import com.polstat.sipemiru.response.RuanganResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    //@GET("ruangans")
    //fun fetchAllRuangan(@Body ruanganRequest: RuanganRequest): Call<RuanganResponse>

    @POST("ruangans")
    fun ruangans(@Body ruanganRequest: RuanganRequest): Call<RuanganResponse>
}