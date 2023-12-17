package com.polstat.sipemiru.service

import android.content.Context
import android.content.SharedPreferences

class SessionManager(private val context: Context): AuthenticationManager {
    private var prefs: SharedPreferences = context.getSharedPreferences("Sipemiru", Context.MODE_PRIVATE)

    companion object{
        const val USER_TOKEN = "user_token"
    }

    fun saveAuthToken(token: String){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun clearAuthToken(){
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }

    override fun getAccessToken(): String {
        return fetchAuthToken() ?: ""
    }
}