package com.polstat.sipemiru.repository

import com.polstat.sipemiru.model.AddPeminjamanForm
import com.polstat.sipemiru.response.PeminjamanResponse
import com.polstat.sipemiru.service.PeminjamanApiService

interface PeminjamanRepository{
    suspend fun addPeminjaman(token: String, peminjaman: AddPeminjamanForm) : PeminjamanResponse
}

class NetworkPeminjamanRepository(private val peminjamanApiService: PeminjamanApiService): PeminjamanRepository {
    override suspend fun addPeminjaman(token: String, peminjaman: AddPeminjamanForm) = peminjamanApiService.addPeminjaman("Bearer $token", peminjaman)
}