package com.polstat.sipemiru.repository

import com.polstat.sipemiru.model.LoginRequest
import com.polstat.sipemiru.model.LoginResponse
import com.polstat.sipemiru.service.RetrofitInstance

class LoginRepository {
    private val loginApiService = RetrofitInstance.loginApiService

    suspend fun requestForLogin(loginRequest: LoginRequest): LoginResponse {
        return loginApiService.login(loginRequest)
    }
}