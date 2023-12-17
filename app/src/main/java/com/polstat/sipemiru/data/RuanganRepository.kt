package com.polstat.sipemiru.data

import android.content.Context
import com.polstat.sipemiru.model.AddRuanganForm
import com.polstat.sipemiru.response.RuanganResponse
import com.polstat.sipemiru.service.RuanganApiService

interface RuanganRepository {
    suspend fun addRuangan(token: String, ruangan: AddRuanganForm) : RuanganResponse
}

class NetworkRuanganRepository(private val ruanganApiService: RuanganApiService): RuanganRepository {
    override suspend fun addRuangan(token: String, ruangan: AddRuanganForm) = ruanganApiService.addRuangan("Bearer $token", ruangan)
}
