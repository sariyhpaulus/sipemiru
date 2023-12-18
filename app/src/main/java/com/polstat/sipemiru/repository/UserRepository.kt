package com.polstat.sipemiru.repository

import com.polstat.sipemiru.response.UserResponse
import com.polstat.sipemiru.service.UserApiService

interface UserRepository{
    suspend fun showProfile(token: String): UserResponse
}

class NetworkUserRepository(private val userApiService: UserApiService): UserRepository{
    override suspend fun showProfile(token: String): UserResponse = userApiService.showProfile("Bearer $token")
}

