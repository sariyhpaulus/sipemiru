package com.polstat.sipemiru.response

import com.polstat.sipemiru.model.Ruangan
import kotlinx.serialization.Serializable

@Serializable
data class ListRuanganResponse (
    val status: Int?,
    val message: String?,
    val data: List<Ruangan>
)