package com.polstat.sipemiru.response

data class RuanganResponse(
    val status: Int?,
    val message: String?,
    val data: DataRuangan?
)

class DataRuangan{
    val ruanganId: String = ""
    val namaRuangan: String = ""
    val gedung: String = ""
    val lantai: String = ""
    val kapasitas: Int = 0
}