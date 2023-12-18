package com.polstat.sipemiru.response

import com.polstat.sipemiru.model.Peminjaman
import kotlinx.serialization.Serializable
import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime

@Serializable
data class PeminjamanResponse(
    val status: Int?,
    val message: String?,
    val data: Peminjaman?
)
