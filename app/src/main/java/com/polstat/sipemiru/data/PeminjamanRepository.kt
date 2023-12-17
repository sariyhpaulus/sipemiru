package com.polstat.sipemiru.data

import com.polstat.sipemiru.request.PeminjamanRequest
import com.polstat.sipemiru.response.PeminjamanResponse
import com.polstat.sipemiru.service.RetrofitInstance

class PeminjamanRepository {
    private val peminjamanApiService = RetrofitInstance.peminjamanApiService

    suspend fun requestForPeminjaman(peminjamanRequest: PeminjamanRequest): PeminjamanResponse {
        return peminjamanApiService.peminjamans(peminjamanRequest)
    }
}
