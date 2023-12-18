package com.polstat.sipemiru.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable


@Serializable
data class AddPeminjamanForm(
    val ruanganId: String,
    val tanggalPeminjaman: String,
    val waktuMulai: String,
    val waktuSelesai: String,
    val keperluan: String
)