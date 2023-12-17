package com.polstat.sipemiru.service

import okhttp3.Interceptor

class AuthInterceptor(private val sessionManager: SessionManager): Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val token = sessionManager.fetchAuthToken()
        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}