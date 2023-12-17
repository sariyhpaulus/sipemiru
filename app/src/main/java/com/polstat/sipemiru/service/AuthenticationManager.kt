package com.polstat.sipemiru.service

interface AuthenticationManager {
    fun getAccessToken(): String
}