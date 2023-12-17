package com.polstat.sipemiru.data

import android.content.Context
import com.polstat.sipemiru.request.RuanganRequest
import com.polstat.sipemiru.response.RuanganResponse
import com.polstat.sipemiru.service.ApiClient
import com.polstat.sipemiru.service.ApiService
import com.polstat.sipemiru.service.RetrofitInstance
import com.polstat.sipemiru.service.SessionManager

class RuanganRepository() {
    //private lateinit var sessionManager: SessionManager
    private val ruanganApiService = RetrofitInstance.ruanganApiService
    //private lateinit var apiClient: ApiClient

    suspend fun requestForRuangan(ruanganRequest: RuanganRequest): RuanganResponse {
        return ruanganApiService.ruangans(ruanganRequest)
//        return apiClient.getApiService().ruangans(ruanganRequest)
    }
}
