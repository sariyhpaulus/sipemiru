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

//object UserRepository {
//    private const val PREF_NAME = "user_data"
//    private const val KEY_ACCESS_TOKEN = "accessToken"
//    private const val KEY_EXPIRES_IN = "expiresIn"
//    private const val KEY_ROLE = "role"
//    private const val KEY_USER_ID = "userId"
//    private const val KEY_EMAIL = "email"
//
//    private lateinit var sharedPreferences: SharedPreferences
//
//    fun initialize(context: Context){
//        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//    }
//
//    var role: String?
//        get() = sharedPreferences.getString(KEY_ROLE, null)
//        set(value) {
//            sharedPreferences.edit().putString(KEY_ROLE, value).apply()
//        }
//
//    var userId: Long?
//        get() = sharedPreferences.getLong(KEY_USER_ID, 0)
//        set(value) {
//            sharedPreferences.edit().putLong(KEY_USER_ID, value ?: 0).apply()
//        }
//
//    var email: String
//        get() = sharedPreferences.getString(KEY_EMAIL, "") ?: ""
//        set(value) {
//            sharedPreferences.edit().putString(KEY_EMAIL, value).apply()
//        }
//
//    var accessToken: String?
//        get() = sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
//        set(value) {
//            sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, value).apply()
//        }
//
//    var expiresIn: Long
//        get() = sharedPreferences.getLong(KEY_EXPIRES_IN, 0)
//        set(value) {
//            sharedPreferences.edit().putLong(KEY_EXPIRES_IN, calculateExpiryTime(value)).apply()
//        }
//
//    fun setAllUserData(accessToken: String, role: String, userId: Long, email: String, expiresIn: Long) {
//        this.accessToken = accessToken
//        this.role = role
//        this.userId= userId
//        this.email = email
//        this.expiresIn = expiresIn
//    }
//    private fun calculateExpiryTime(expiresIn: Long): Long {
//        return System.currentTimeMillis() + expiresIn * 1000
//    }
//
//    override fun toString(): String {
//        return "UserRepository(" +
//                "accessToken=$accessToken, " +
//                "role=$role, " +
//                "userId=$userId, " +
//                "email=$email, " +
//                "expiresIn=$expiresIn" +
//                ")"
//    }
//
//}