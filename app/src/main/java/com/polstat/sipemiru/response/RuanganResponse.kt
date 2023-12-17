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

//class DataRuangan{
//    val ruanganId: String = ""
//    val namaRuangan: String = ""
//    val gedung: String = ""
//    val lantai: String = ""
//    val kapasitas: Int = 0
//}