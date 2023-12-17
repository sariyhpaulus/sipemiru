package com.polstat.sipemiru.data

import com.polstat.sipemiru.request.RuanganRequest
import com.polstat.sipemiru.response.RuanganResponse
import com.polstat.sipemiru.service.RetrofitInstance
import com.polstat.sipemiru.service.SessionManager

class RuanganRepository() {
    private val ruanganApiService = RetrofitInstance.ruanganApiService

//    fun fetchAuthToken(): String? {
//        return sessionManager.fetchAuthToken()
//    }

    suspend fun requestForRuangan(ruanganRequest: RuanganRequest): RuanganResponse {

        return ruanganApiService.ruangans(ruanganRequest)
    }
}