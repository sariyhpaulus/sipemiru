package com.polstat.sipemiru.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private const val BASE_URL = "http://192.168.1.11:8081"

    private val gson : Gson = GsonBuilder()
        .setLenient()
        .create()

    // Function to create OkHttpClient with AuthInterceptor
//    private val okHttpClient : OkHttpClient by lazy {
//        OkHttpClient.Builder()
//            .addInterceptor(AuthInterceptor(sessionManager = SessionManager()))
//            .build()
//    }
    private fun createOkHttpClient(sessionManager: SessionManager): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(sessionManager))
            .build()
    }

    private val retrofit : Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val loginApiService: LoginApiService by lazy {
        retrofit.create(LoginApiService::class.java)
    }

    val profileApiService: ProfileApiService by lazy {
        retrofit.create(ProfileApiService::class.java)
    }

    val ruanganApiService: RuanganApiService by lazy {
        retrofit.create(RuanganApiService::class.java)
    }

//    fun createRuanganApiService(sessionManager: SessionManager): RuanganApiService {
//        val okHttpClient = createOkHttpClient(sessionManager)
//        return retrofit.newBuilder().client(okHttpClient).build().create(RuanganApiService::class.java)
//    }
}