package com.polstat.sipemiru.response

import com.polstat.sipemiru.model.Peminjaman
import kotlinx.serialization.Serializable

@Serializable
data class ListPeminjamanResponse (
    val status: Int?,
    val message: String?,
    val data: List<Peminjaman>
)