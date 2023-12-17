package com.polstat.sipemiru.model

import kotlinx.serialization.Serializable

@Serializable
data class AddRuanganForm(
    val ruanganId: String,
    val namaRuangan: String,
    val gedung: String,
    val lantai: String,
    val kapasitas: String
)