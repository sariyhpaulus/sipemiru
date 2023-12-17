package com.polstat.sipemiru.ui.peminjaman

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polstat.sipemiru.data.PeminjamanRepository
import com.polstat.sipemiru.request.PeminjamanRequest
import com.polstat.sipemiru.response.PeminjamanResponse
import com.polstat.sipemiru.response.RuanganResponse
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class PeminjamanViewModel(): ViewModel() {
    private val peminjamanRepository = PeminjamanRepository()
    private val _peminjamanResponse = MutableLiveData<PeminjamanResponse>()
    val peminjamanResponse : MutableLiveData<PeminjamanResponse> = _peminjamanResponse

    fun requestPeminjaman(
        ruanganId: String,
        tanggalPeminjaman: LocalDate,
        waktuMulai: LocalTime,
        waktuSelesai: LocalTime,
        keperluan: String
    ){
        println("berhasil")
        viewModelScope.launch {
            try {
                println("berhasil 2")
                val response = peminjamanRepository.requestForPeminjaman(PeminjamanRequest(ruanganId, tanggalPeminjaman, waktuMulai, waktuSelesai, keperluan))
                _peminjamanResponse.value = response
                println(response)
            } catch (e: Exception) {
                println("Error ini:"+e.message)
            }
        }
    }

}