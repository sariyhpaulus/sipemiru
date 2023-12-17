package com.polstat.sipemiru.response

import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime

data class PeminjamanResponse(
    val status: Int?,
    val message: String?,
    val data: DataPeminjaman?
)

class DataPeminjaman{
    val peminjamanId: Int = 0
    val emailPeminjam: String = ""
    val ruanganId: String = ""
    val tanggalPeminjaman: LocalDate? = null
    val waktuMulai: LocalTime? = null
    val waktuSelesai: LocalTime? = null
    val keperluan: String = ""
    val status: String = ""
}