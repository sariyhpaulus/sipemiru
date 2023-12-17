package com.polstat.sipemiru.model

import kotlinx.serialization.Serializable

@Serializable
data class Ruangan(
    val ruanganId: String,
    val namaRuangan: String,
    val gedung: String,
    val lantai: String,
    val kapasitas: String
)