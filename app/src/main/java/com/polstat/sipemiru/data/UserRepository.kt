package com.polstat.sipemiru.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.polstat.sipemiru.response.ProfileResponse
import com.polstat.sipemiru.service.RetrofitInstance
import retrofit2.http.Header

class UserRepository{
    private val showProfileService = RetrofitInstance.profileApiService

//    suspend fun requestForProfile():ProfileResponse{
//        return showProfileService.getProfile()
//    }

    suspend fun getProfile(token: String): ProfileResponse {
        return showProfileService.getProfile(token)
    }
}
