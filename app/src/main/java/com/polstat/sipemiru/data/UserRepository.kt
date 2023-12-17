package com.polstat.sipemiru.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.polstat.sipemiru.response.ProfileResponse
import com.polstat.sipemiru.response.UserResponse
import com.polstat.sipemiru.service.RetrofitInstance
import com.polstat.sipemiru.service.UserApiService
import retrofit2.http.Header

interface UserRepository{
    suspend fun showProfile(token: String): UserResponse
}

class NetworkUserRepository(private val userApiService: UserApiService): UserRepository{
    override suspend fun showProfile(token: String): UserResponse {
        return userApiService.showProfile(token)
    }

}

