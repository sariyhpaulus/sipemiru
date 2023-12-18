package com.polstat.sipemiru.repository

import com.polstat.sipemiru.model.EditProfileForm
import com.polstat.sipemiru.model.RegisterForm
import com.polstat.sipemiru.response.UserResponse
import com.polstat.sipemiru.service.UserApiService

interface UserRepository{
    suspend fun showProfile(token: String): UserResponse
    suspend fun register(user: RegisterForm)
    suspend fun editProfile(editProfile: EditProfileForm)
}

class NetworkUserRepository(private val userApiService: UserApiService): UserRepository{
    override suspend fun showProfile(token: String): UserResponse = userApiService.showProfile("Bearer $token")
    override suspend fun register(user: RegisterForm) = userApiService.register(user)
    override suspend fun editProfile(editProfile: EditProfileForm) = userApiService.editProfile(editProfile)
}

