package com.polstat.sipemiru.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalTime


@Serializable
data class Peminjaman(
    val ruanganId: String,
    val emailPeminjam: String,
    val tanggalPeminjaman: String,
    val waktuMulai: String,
    val waktuSelesai: String,
    val keperluan: String,
    val status: String
)