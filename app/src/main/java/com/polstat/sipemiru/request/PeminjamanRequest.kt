package com.polstat.sipemiru.request

import java.time.LocalDate
import java.time.LocalTime
import java.util.Date

data class PeminjamanRequest (
    val ruanganId: String,
    val tanggalPeminjaman: LocalDate,
    val waktuMulai: LocalTime,
    val waktuSelesai: LocalTime,
    val keperluan: String
)

//"ruanganId":"G1AUDIT",
//"tanggalPeminjaman":"2023-10-21",
//"waktuMulai":"08:00:00",
//"waktuSelesai":"13:00:00",
//"keperluan":"Gladi Bersih Acara Angkatan"