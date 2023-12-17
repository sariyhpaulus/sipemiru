package com.polstat.sipemiru.service

import com.polstat.sipemiru.request.RuanganRequest
import com.polstat.sipemiru.response.RuanganResponse
import com.polstat.sipemiru.service.SessionManager.Companion.USER_TOKEN
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RuanganApiService {
    @Headers("Authorization: Bearer"+ "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjFAZXhhbXBsZS5jb20iLCJyb2xlcyI6WyJST0xFX0FETUlOIl0sImlzcyI6IlBvbHN0YXQiLCJpYXQiOjE3MDI3NjYxMDcsImV4cCI6MTcwMjg1MjUwN30.hoBDhFWHBa5fwuUI1pRM7o1aYwRbx-pMWiErjsN5wxp9mDw7hLr1OAPj1KCvFFTWvcHo7Rao8ldEDTd7rYEb1Q")
    @POST("ruangans")
    suspend fun ruangans(@Body RuanganRequest: RuanganRequest): RuanganResponse
}