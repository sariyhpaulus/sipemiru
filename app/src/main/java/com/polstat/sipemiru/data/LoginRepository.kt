package com.polstat.sipemiru.data

import com.polstat.sipemiru.request.LoginRequest
import com.polstat.sipemiru.response.LoginResponse
import com.polstat.sipemiru.service.RetrofitInstance

class LoginRepository {
    private val loginApiService = RetrofitInstance.loginApiService

    suspend fun requestForLogin(loginRequest: LoginRequest): LoginResponse {
        return loginApiService.login(loginRequest)
    }
}