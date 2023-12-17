package com.polstat.sipemiru.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.polstat.sipemiru.data.NetworkRuanganRepository
import com.polstat.sipemiru.data.NetworkUserRepository
import com.polstat.sipemiru.data.PeminjamanRepository
import com.polstat.sipemiru.data.RuanganRepository
import com.polstat.sipemiru.data.UserRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

interface AppContainer {
    val userRepository: UserRepository
    val ruanganRepository: RuanganRepository
    val peminjamanRepository: PeminjamanRepository
}

class DefaultAppContainer() : AppContainer {
    private val baseUrl = "http://192.168.1.41:8081/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build())
        .build()

    //API Service
    private val userApiService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }

    private val ruanganApiService: RuanganApiService by lazy {
        retrofit.create(RuanganApiService::class.java)
    }

    //Repository
    override val userRepository: UserRepository by lazy {
        NetworkUserRepository(userApiService)
    }

    override val ruanganRepository: RuanganRepository by lazy {
        NetworkRuanganRepository(ruanganApiService)
    }
    override val peminjamanRepository: PeminjamanRepository by lazy {
        retrofit.create(PeminjamanRepository::class.java)
    }

}