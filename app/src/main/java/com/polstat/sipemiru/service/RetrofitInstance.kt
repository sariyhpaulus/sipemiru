package com.polstat.sipemiru.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private const val BASE_URL = "http://192.168.203.33:8081"

    private val gson : Gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
     }

    val loginApiService: LoginApiService by lazy {
        retrofit.create(LoginApiService::class.java)
    }
}