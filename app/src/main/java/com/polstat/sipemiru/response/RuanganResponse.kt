package com.polstat.sipemiru.response

import com.polstat.sipemiru.model.Ruangan
import kotlinx.serialization.Serializable

//data class RuanganResponse(
//    val status: Int?,
//    val message: String?,
//    val data: DataRuangan?
//)

@Serializable
data class RuanganResponse (
    val status: Int?,
    val message: String?,
    val data: Ruangan?
)
